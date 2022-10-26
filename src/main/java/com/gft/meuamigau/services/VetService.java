package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_VET;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_VETERINARIO_PARA_PESSOA_INFORMADA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.VETERINARIO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.VETERINARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.vet.QueryVetDTO;
import com.gft.meuamigau.dtos.vet.RecordVetDTO;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.repositories.VetRepository;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VetService {

	private final VetRepository vetRepository;
	
	private final PersonRepository personRepository;
	
	private final UserService userService;
	
	/**
	 * CRUD: Read
	 * Find vet by id and returns EntityDtoBox<Vet, QueryVetDTO>
	 * @param Long id
	 * @return EntityDtoBox<Vet, QueryVetDTO>
	 */
	public EntityDtoBox<Vet, QueryVetDTO> findById(Long id, EntityDtoBoxType returnType) {
		Vet vet = vetRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						String.format(VETERINARIO_NAO_ENCONTRADO_BY_ID, id)));
		if(returnType.equals(ENTITY)){
			return new EntityDtoBox<>(vet,returnType);
		}
		return new EntityDtoBox<>(VetMapper.toQueryDTO(vet),returnType);
	}
	public EntityDtoBox<Vet, QueryVetDTO> findById(Long id) {
		return findById(id, DTO);
	}
	
	/**
	 * CRUD: Read
	 * Find all vets in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryVetDTO>
	 */
	public Page<QueryVetDTO> findAll(Pageable pageable) {
		Page<Vet> vetPage = vetRepository.findAll(pageable);
		
		List<QueryVetDTO> dtos = vetPage.getContent().stream()
										.map(VetMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, vetPage.getTotalElements());
	}

	/**
	 * CRUD: Create
	 * Create vet and persist in database. It will automatically to assign ROLE_VET to user registered
	 * in respective person
	 * @param RecordVetDTO givenVetDTO
	 * @return QueryVetDTO
	 */
	public QueryVetDTO create(RecordVetDTO givenVetDTO) {
		
		Vet vet = VetMapper.toEntity(givenVetDTO);
		
		if (givenVetDTO.getRegistrationDate() == null) {
			vet.setRegistrationDate(DateTimeConverter.dataAtual());
		}
		
		this.validatePerson(vet, givenVetDTO.getPersonId());
		
		Vet savedVet = vetRepository.save(vet);
		
		this.addRoleVetInUser(vet);
		
		return VetMapper.toQueryDTO(savedVet);
	}
	
	/**
	 * CRUD: Update
	 * Update existing vet
	 * @param RecordVetDTO givenVetDTO
	 * @return QueryVetDTO
	 */
	public QueryVetDTO update(Long id, RecordVetDTO givenVetDTO) {
		
		Vet existingVet = this.findById(id, ENTITY).getEntity();
		
		Vet currentVet = VetMapper.toEntity(givenVetDTO);
		currentVet.setId(id);
		currentVet.setPerson(existingVet.getPerson());
		currentVet.setRegistrationDate(existingVet.getRegistrationDate());
		
		if(!givenVetDTO.getPersonId().equals(existingVet.getPerson().getId()))
			this.validatePerson(currentVet, givenVetDTO.getPersonId());
		
		if (givenVetDTO.getRegistrationDate() != null) {
			currentVet.setRegistrationDate(
					DateTimeConverter.stringtoDate(
							givenVetDTO.getRegistrationDate(), DATE_PATTERN));
		}
		
		Vet updatedVet = vetRepository.save(currentVet);
		
		return VetMapper.toQueryDTO(updatedVet);
	}
	

	/**
	 * CRUD: Delete
	 * Delete vet
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		Vet vet = this.findById(id, ENTITY).getEntity();
		
		if (!vet.getAttendances().isEmpty())
			throw new DataIntegrityViolationException(
					String.format(VETERINARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
							, vet.getId()
							, vet.getAttendances().stream()
    							.map(dog -> dog.getId().toString())
    							.collect(Collectors.joining(","))));
			
		vetRepository.deleteById(id);
		this.deleteRoleVetInUser(vet);
	}
	
	
	
	protected void validatePerson(Vet vet, Long personId) {
		if (vetRepository.existsByPersonId(personId))
			throw new DataIntegrityViolationException(
					String.format(JA_EXISTE_VETERINARIO_PARA_PESSOA_INFORMADA, personId));
		
		this.setPersonInVet(vet, personId);
	}
	
	protected void setPersonInVet(Vet vet, Long personId) {
		vet.setPerson(personRepository.findById(personId).orElseThrow(
						() -> new EntityNotFoundException(
								String.format(PESSOA_NAO_ENCONTRADA_BY_ID
										, personId))));
	}
	
	protected void addRoleVetInUser(Vet vet) {
		userService.addRoleToUser(vet.getPerson().getUser().getId(), ROLE_VET);
	}
	
	protected void deleteRoleVetInUser(Vet vet) {
		userService.removeRoleToUser(vet.getPerson().getUser().getId(), ROLE_VET);
	}
}
