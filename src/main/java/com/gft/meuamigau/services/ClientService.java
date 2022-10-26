package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_CLIENT;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_CACHORRO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CLIENTE_PARA_PESSOA_INFORMADA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.utils.DateTimeConverter.DATE_PATTERN;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.ClientRepository;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {

	private final ClientRepository clientRepository;
	
	private final PersonRepository personRepository;
	
	private final UserService userService;
	
	/**
	 * CRUD: Read
	 * Find client by id and returns EntityDtoBox<Client, QueryClientDTO>
	 * @param Long id
	 * @return EntityDtoBox<Client, QueryClientDTO>
	 */
	public EntityDtoBox<Client, QueryClientDTO> findById(Long id, EntityDtoBoxType returnType) {
		Client client = clientRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(
						String.format(CLIENTE_NAO_ENCONTRADO_BY_ID, id)));
		if(returnType.equals(ENTITY)){
			return new EntityDtoBox<>(client,returnType);
		}
		return new EntityDtoBox<>(ClientMapper.toQueryDTO(client),returnType);
	}
	public EntityDtoBox<Client, QueryClientDTO> findById(Long id) {
		return findById(id, DTO);
	}
	
	/**
	 * CRUD: Read
	 * Find all clients in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryClientDTO>
	 */
	public Page<QueryClientDTO> findAll(Pageable pageable) {
		Page<Client> clientPage = clientRepository.findAll(pageable);
		
		List<QueryClientDTO> dtos = clientPage.getContent().stream()
										.map(ClientMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, clientPage.getTotalElements());
	}

	/**
	 * CRUD: Create
	 * Create client and persist in database. It will automatically to assign ROLE_CLIENT to user registered
	 * in respective person
	 * @param RecordClientDTO givenClientDTO
	 * @return QueryClientDTO
	 */
	public QueryClientDTO create(RecordClientDTO givenClientDTO) {
		
		Client client = ClientMapper.toEntity(givenClientDTO);
		
		if (givenClientDTO.getRegistrationDate() == null) {
			client.setRegistrationDate(DateTimeConverter.dataAtual());
		}
		
		this.validatePerson(client, givenClientDTO.getPersonId());
		
		Client savedClient = clientRepository.save(client);
		
		this.addRoleClientInUser(client);
		
		return ClientMapper.toQueryDTO(savedClient);
	}
	
	/**
	 * CRUD: Update
	 * Update existing client
	 * @param RecordClientDTO givenClientDTO
	 * @return QueryClientDTO
	 */
	public QueryClientDTO update(Long id, RecordClientDTO givenClientDTO) {
		
		Client existingClient = this.findById(id, ENTITY).getEntity();
		
		Client currentClient = new Client();
		currentClient.setId(id);
		currentClient.setPerson(existingClient.getPerson());
		currentClient.setRegistrationDate(existingClient.getRegistrationDate());
		
		if(!givenClientDTO.getPersonId().equals(existingClient.getPerson().getId()))
			this.validatePerson(currentClient, givenClientDTO.getPersonId());
		
		if (givenClientDTO.getRegistrationDate() != null) {
			currentClient.setRegistrationDate(
					DateTimeConverter.stringtoDate(
							givenClientDTO.getRegistrationDate(), DATE_PATTERN));
		}
		
		Client updatedClient = clientRepository.save(currentClient);
		
		return ClientMapper.toQueryDTO(updatedClient);
	}
	

	/**
	 * CRUD: Delete
	 * Delete client
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		Client existingClient = this.findById(id, ENTITY).getEntity();
		
		if (!existingClient.getDogs().isEmpty())
			throw new DataIntegrityViolationException(
					String.format(CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_CACHORRO
							, existingClient.getId()
							, existingClient.getDogs().stream()
								.map(dog -> dog.getId().toString())
								.collect(Collectors.joining(","))));
		
		if (!existingClient.getAttendances().isEmpty())
			throw new DataIntegrityViolationException(
					String.format(CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
							, existingClient.getId()
							, existingClient.getAttendances().stream()
    							.map(attendance -> attendance.getId().toString())
    							.collect(Collectors.joining(","))));
			
		clientRepository.deleteById(id);
		removeRoleClientInUser(existingClient);
	}
	
	
	
	protected void validatePerson(Client client, Long personId) {
		if (clientRepository.existsByPersonId(personId))
			throw new DataIntegrityViolationException(
					String.format(JA_EXISTE_CLIENTE_PARA_PESSOA_INFORMADA, personId));
		
		this.setPersonInClient(client, personId);
	}
	
	protected void setPersonInClient(Client client, Long personId) {
		client.setPerson(personRepository.findById(personId).orElseThrow(
						() -> new EntityNotFoundException(
								String.format(PESSOA_NAO_ENCONTRADA_BY_ID
										, personId))));
	}
	
	protected void addRoleClientInUser(Client client) {
		userService.addRoleToUser(client.getPerson().getUser().getId(), ROLE_CLIENT);
	}
	
	protected void removeRoleClientInUser(Client client) {
		userService.removeRoleToUser(client.getPerson().getUser().getId(), ROLE_CLIENT);
	}
}
