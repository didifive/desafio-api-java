package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CACHORRO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CACHORRO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.DogRepository;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DogService {

	private final DogRepository dogRepository;
	
	private final ClientService clientService;
	
	private final BreedService breedService;
	
	/**
	 * CRUD: Read
	 * Find dog by id and returns EntityDtoBox<Dog, QueryDogDTO>
	 * @param Long id
	 * @return EntityDtoBox<Dog, QueryDogDTO>
	 */
	public EntityDtoBox<Dog, QueryDogDTO> findById(Long id, EntityDtoBoxType returnType) {
		Dog dog = dogRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						String.format(CACHORRO_NAO_ENCONTRADO_BY_ID, id)));
		if(returnType.equals(ENTITY)){
			return new EntityDtoBox<>(dog,returnType);
		}
		return new EntityDtoBox<>(DogMapper.toQueryDTO(dog),returnType);
	}
	public EntityDtoBox<Dog, QueryDogDTO> findById(Long id) {
		return findById(id, DTO);
	}
	
	/**
	 * CRUD: Read
	 * Find all dogs in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryDogDTO>
	 */
	public Page<QueryDogDTO> findAll(Pageable pageable) {
		Page<Dog> dogPage = dogRepository.findAll(pageable);
		
		List<QueryDogDTO> dtos = dogPage.getContent().stream()
										.map(DogMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, dogPage.getTotalElements());
	}
	
	/**
	 * CRUD: Read
	 * Find all dogs by client in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryDogDTO>
	 */
	public Page<QueryDogDTO> findAllByClientId(Long clientId, Pageable pageable) {
		Page<Dog> dogPage = dogRepository.findByClientId(clientId, pageable);
		
		List<QueryDogDTO> dtos = dogPage.getContent().stream()
										.map(DogMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, dogPage.getTotalElements());
	}

	/**
	 * CRUD: Create
	 * Create dog and persist in database for respective client
	 * @param RecordDogDTO givenDogDTO
	 * @return QueryDogDTO
	 */
	public QueryDogDTO create(RecordDogDTO givenDogDTO) {
		
		Dog dog = DogMapper.toEntity(givenDogDTO);
		
		if (givenDogDTO.getRegistrationDate() == null) {
			dog.setRegistrationDate(DateTimeConverter.dataAtual());
		}
		
		setClientAndBreed(dog);
		
		Dog savedDog = dogRepository.save(dog);
		
		return DogMapper.toQueryDTO(savedDog);
	}
	
	/**
	 * CRUD: Update
	 * Update existing dog
	 * @param RecordDogDTO givenDogDTO
	 * @return QueryDogDTO
	 */
	public QueryDogDTO update(Long id, RecordDogDTO givenDogDTO) {
		
		Dog existingDog = this.findById(id, ENTITY).getEntity();
		
		Dog currentDog = DogMapper.toEntity(givenDogDTO);
		currentDog.setId(id);
		setClientAndBreed(currentDog);
		if (givenDogDTO.getRegistrationDate() != null) {
			currentDog.setRegistrationDate(
					DateTimeConverter.stringtoDate(
							givenDogDTO.getRegistrationDate(), DATE_PATTERN));
		} else {
			currentDog.setRegistrationDate(existingDog.getRegistrationDate());
		}
		
		Dog updatedDog = dogRepository.save(currentDog);
		
		return DogMapper.toQueryDTO(updatedDog);
	}
	

	/**
	 * CRUD: Delete
	 * Delete dog
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		Dog existingDog = this.findById(id, ENTITY).getEntity();
		
		if (!existingDog.getAttendances().isEmpty())
			throw new DataIntegrityViolationException(
					String.format(CACHORRO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
							, existingDog.getId()
							, existingDog.getAttendances().stream()
    							.map(attendance -> attendance.getId().toString())
    							.collect(Collectors.joining(","))));
			
		dogRepository.deleteById(id);
	}
	
	
	private void setClientAndBreed(Dog dog) {
		dog.setClient(clientService.findById(dog.getClient().getId(),ENTITY).getEntity());
		dog.setBreed(breedService.findById(dog.getBreed().getId(),ENTITY).getEntity());
	}
}
