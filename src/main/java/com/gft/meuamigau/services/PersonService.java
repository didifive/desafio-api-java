package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_PERSON;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CADASTRO_COM_O_CPF_OU_CNPJ_INFORMADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CADASTRO_COM_O_E_MAIL_INFORMADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_JA_ESTA_VINCULADA_COM_O_USUARIO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_CLIENTE;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_VETERINARIO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_JA_VINCULADO_COM_OUTRA_PESSOA;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.person.DetailsPersonDTO;
import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.entities.Address;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PersonService {
	
	private final PersonRepository personRepository;
	
	private final UserService userService;
	
	/**
	 * CRUD: Read
	 * Find person by id and returns details with DetailsPersonDTO
	 * @param Long id
	 * @return DetailsPersonDTO
	 */
	public EntityDtoBox<Person, DetailsPersonDTO> findById(Long id, EntityDtoBoxType returnType) {
		Person person = personRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundException(
						String.format(PESSOA_NAO_ENCONTRADA_BY_ID, id)));
		if(returnType.equals(ENTITY))
			return new EntityDtoBox<>(person, ENTITY);
		
		return new EntityDtoBox<>(PersonMapper.toDetailsDTO(person),DTO);
	}
	public EntityDtoBox<Person, DetailsPersonDTO> findById(Long id) {
		return this.findById(id, DTO);
	}

	/**
	 * CRUD: Read
	 * Find all people in database and return content with pagination
	 * @param Pageable pageable
	 * @return Page<QueryPersonDTO>
	 */
	public Page<QueryPersonDTO> findAll(Pageable pageable) {
		Page<Person> peoplePage = personRepository.findAll(pageable);
		
		List<QueryPersonDTO> dtos = peoplePage.getContent().stream()
										.map(PersonMapper::toQueryDTO)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, peoplePage.getTotalElements());
	}

	/**
	 * CRUD: Create
	 * Create person and persist in database.
	 * It will automatically create a User with email as username cpfCnpj as password and ROLE_PERSON
	 * @param RecordDogDTO givenPersonDTO
	 * @return QueryPersonDTO
	 */
	public QueryPersonDTO create(RecordPersonDTO givenPersonDTO) {
		Person person = PersonMapper.toEntity(givenPersonDTO);
		
		if (personRepository.existsByCpfCnpj(person.getCpfCnpj()))
			throw new DataIntegrityViolationException(
					String.format(JA_EXISTE_CADASTRO_COM_O_CPF_OU_CNPJ_INFORMADO,person.getCpfCnpj()));
		
		if (personRepository.existsByEmailIgnoreCase(person.getEmail()))
			throw new DataIntegrityViolationException(
					String.format(JA_EXISTE_CADASTRO_COM_O_E_MAIL_INFORMADO,person.getEmail()));
		
		person.getAddresses().forEach(a -> a.setPerson(person));
		
		person.setUser(createUserForPerson(person.getEmail(), person.getCpfCnpj()));
		
		person.getUser().setPerson(person);
		
		Person savedPerson = personRepository.save(person);
		
		return PersonMapper.toQueryDTO(savedPerson);
	}

	/**
	 * Create a User with ROLE_PERSON. 
	 * If email already registred as username in a existing User, the createUserForPerson method will be 
	 * called recursively changing the username suggestion until there is no exists in already registred User 
	 * @param String username
	 * @param String password
	 * @return User
	 */
	protected User createUserForPerson(String username, String password) {
		
		RecordUserDTO recordUserDTO = new RecordUserDTO(username, password, List.of(ROLE_PERSON));
		
		EntityDtoBox<User, QueryUserDTO> entityDtoBox = new EntityDtoBox<>();

		try {
			entityDtoBox = userService.create(recordUserDTO, ENTITY);
		} catch (DataIntegrityViolationException e) {
			final String splitter = "@";
			String[] split = username.split(splitter);
			split[0] += "1";
			username = String.join(splitter, split);
			return createUserForPerson(username, password);
		}
		
		return entityDtoBox.getEntity();
	}

	/**
	 * CRUD: Update
	 * Update person with givenPersonDTO and persist in database
	 * @param RecordDogDTO givenPersonDTO
	 * @return QueryPersonDTO
	 */
	public QueryPersonDTO update(Long id, RecordPersonDTO givenPersonDTO) {
		Person existingPerson = this.findById(id, ENTITY).getEntity();
		
		Person currentPerson = redeemAttributesPerson(givenPersonDTO, existingPerson);
		
		currentPerson.getAddresses().forEach(address -> {
			address.setPerson(currentPerson);
			for (Address existingAddress : existingPerson.getAddresses()) {
				if(address.equals(existingAddress)) {
					address.setId(existingAddress.getId());
				}
			}
		});
		
		Person savedPerson = personRepository.save(currentPerson);
		
		return PersonMapper.toQueryDTO(savedPerson);
	}

	private Person redeemAttributesPerson(RecordPersonDTO givenPersonDTO, Person existingPerson) {
		Person currentPerson = PersonMapper.toEntity(givenPersonDTO);
		currentPerson.setId(existingPerson.getId());
		currentPerson.setUser(existingPerson.getUser());
		currentPerson.setVet(existingPerson.getVet());
		currentPerson.setClient(existingPerson.getClient());
		return currentPerson;
	}

	/**
	 * CRUD: Delete
	 * Delete person
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		Person existingPerson = this.findById(id, ENTITY).getEntity();
		
		if (existingPerson.getClient() != null)
			throw new DataIntegrityViolationException(
					String.format(PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_CLIENTE
							, existingPerson.getId(), existingPerson.getClient().getId()));
		
		if (existingPerson.getVet() != null)
			throw new DataIntegrityViolationException(
					String.format(PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_VETERINARIO
							, existingPerson.getId(), existingPerson.getVet().getId()));
			
		personRepository.delete(existingPerson);
		userService.removeRoleToUser(existingPerson.getUser().getId(), ROLE_PERSON);
	}
	
	/**
	 * CRUD: Update
	 * Handle user by id in person
	 * @param Long idPerson
	 * @param Long idNewUser
	 * @return void
	 */
	public void handleUser(Long idPerson, Long newUserId) {
		Person existingPerson = this.findById(idPerson, ENTITY).getEntity();
		
		if (existingPerson.getUser().getId().equals(newUserId))
			throw new DataIntegrityViolationException(
					String.format(PESSOA_JA_ESTA_VINCULADA_COM_O_USUARIO
							, existingPerson.getId(), newUserId));
		
		if (personRepository.existsByUserId(newUserId))
			throw new DataIntegrityViolationException(
					String.format(USUARIO_JA_VINCULADO_COM_OUTRA_PESSOA
							, newUserId));
		
		User newUser = userService.findById(newUserId, ENTITY).getEntity();
		
		Long idOldUser = existingPerson.getUser().getId();
		existingPerson.setUser(newUser);
		
		personRepository.save(existingPerson);
		
		userService.removeRoleToUser(idOldUser, ROLE_PERSON);
		userService.addRoleToUser(newUserId, ROLE_PERSON);
	}
	
	/**
	 * CRUD: Update
	 * Handle user by username in person
	 * @param Long idPerson
	 * @param String username
	 * @return void
	 */
	public void handleUser(Long idPerson, String username) {
		User newUser = userService.findByUsername(username);
		
		handleUser(idPerson, newUser.getId());
	}
	
}
