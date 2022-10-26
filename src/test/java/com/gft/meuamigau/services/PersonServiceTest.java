package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_PERSON;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CADASTRO_COM_O_CPF_OU_CNPJ_INFORMADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CADASTRO_COM_O_E_MAIL_INFORMADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_JA_ESTA_VINCULADA_COM_O_USUARIO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_CLIENTE;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_VETERINARIO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_JA_VINCULADO_COM_OUTRA_PESSOA;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.PersonUtils.CPF_CNPJ;
import static com.gft.meuamigau.utils.PersonUtils.DATE_STRING;
import static com.gft.meuamigau.utils.PersonUtils.EMAIL;
import static com.gft.meuamigau.utils.PersonUtils.ID;
import static com.gft.meuamigau.utils.PersonUtils.NAME;
import static com.gft.meuamigau.utils.PersonUtils.PERSON_TYPE;
import static com.gft.meuamigau.utils.PersonUtils.PHONE;
import static com.gft.meuamigau.utils.PersonUtils.QUERY_ADDRESSES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gft.meuamigau.dtos.person.DetailsPersonDTO;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.PersonUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> PersonService")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	
	private Person person;
	private RecordPersonDTO givenPersonDTO;
	
	private static final Long INVALID_ID = 2L;
	private static final Long ID_NEW = 3L;
	private static final String NEW_EMAIL = "joaodasilva1@gft.com";
	
	@Mock
    private PersonRepository personRepository;
	
	@Mock
	private UserService userService;
	
	@InjectMocks @Spy
    private PersonService personService;
	
	@BeforeEach
	private void setup() {
		person = PersonUtils.createFakeEntity();
		givenPersonDTO = PersonUtils.createFakeRecordPersonDTO();
	}
	
	private void assertQueryPersonDTO(QueryPersonDTO result) {
		assertAll("assert QueryPersonDTO"
				, () -> assertNotNull(result)
				, () -> assertInstanceOf(QueryPersonDTO.class, result)
				, () -> assertEquals(ID, 				result.getId())
				, () -> assertEquals(NAME, 				result.getName())
				, () -> assertEquals(PERSON_TYPE, 		result.getPersonType())
				, () -> assertEquals(CPF_CNPJ, 			result.getCpfCnpj())
				, () -> assertEquals(EMAIL, 			result.getEmail())
				, () -> assertEquals(DATE_STRING, 		result.getBirthDate())
				, () -> assertEquals(PHONE, 			result.getPhone())
				, () -> assertEquals(QUERY_ADDRESSES,	result.getAddresses())
				
		);
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar DetaisPersonDTO")
    void whenFindByIdThenShouldReturnDetaisPersonDTO() {
		//when
        when(personRepository.findById(ID)).thenReturn(Optional.of(person));
        
        //then
        DetailsPersonDTO foundedPerson = personService.findById(ID).getDto();
        
        assertAll(
        		"Check if DetailsPersonDTO was returned"
        		, () -> assertNotNull(foundedPerson)
        		, () -> assertEquals(DetailsPersonDTO.class, foundedPerson.getClass())
        		, () -> assertEquals(ID, 				foundedPerson.getId())
        		, () -> assertEquals(NAME, 				foundedPerson.getName())
        		, () -> assertEquals(PERSON_TYPE, 		foundedPerson.getPersonType())
        		, () -> assertEquals(CPF_CNPJ, 			foundedPerson.getCpfCnpj())
        		, () -> assertEquals(EMAIL, 			foundedPerson.getEmail())
        		, () -> assertEquals(DATE_STRING, 		foundedPerson.getBirthDate())
        		, () -> assertEquals(PHONE, 			foundedPerson.getPhone())
        		, () -> assertEquals(QUERY_ADDRESSES,	foundedPerson.getAddresses())
        		, () -> assertEquals(EMAIL,				foundedPerson.getUsername())
        );
    }
	
	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidNameThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> personService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(PESSOA_NAO_ENCONTRADA_BY_ID
						,INVALID_ID));    
	}

	
	@Test
	@DisplayName("2. Quando procurar todas as pessoas, deve retornar paginação de QueryPersonDTO")
    void whenSearchingForAllPeopleShouldReturnPaginationOfQueryPersonDTO() {
		//given
		Page<Person> personPage = new PageImpl<>(List.of(person));
		Pageable pageable = PageRequest.of(0, 8);
		QueryPersonDTO expectedPersonDTO = PersonUtils.createFakeQueryPersonDTO();
		
		//when
        when(personRepository.findAll(any(Pageable.class))).thenReturn(personPage);
        
        //then
        Page<QueryPersonDTO> result = personService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryPersonDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryPersonDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedPersonDTO)
        );
    }
	
	@Test
	@DisplayName("3. Quando criar pessoa, deve retornar QueryPersonDTO")
    void whenCreatePersonShouldReturnQueryPersonDTO() {
		//given
		User newUser = new User(ID, EMAIL, CPF_CNPJ, new HashSet<>(),null,new ArrayList<>());
		
		//when
        doReturn(newUser).when(personService).createUserForPerson(EMAIL, CPF_CNPJ);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        
        //then
        QueryPersonDTO result = personService.create(givenPersonDTO);
        assertAll(
        		"Check if user saved and QueryUserDTO was returned"
        		, () -> verify(personRepository,times(1)).save(any(Person.class))
        		, () -> assertQueryPersonDTO(result)
        		, () -> assertEquals(EMAIL, result.getUsername())
        );
    }
	
	@Test
	@DisplayName("3.2. Quando criar pessoa com CPF ou CNPJ existente, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenCreatePersonWithExistingCPFOrCNPJShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
        when(personRepository.existsByCpfCnpj(anyString())).thenReturn(Boolean.TRUE);
        
        //then
		assertThrowsExceptionWithCorrectMessage(() -> personService.create(givenPersonDTO)
				, DataIntegrityViolationException.class
				, String.format(JA_EXISTE_CADASTRO_COM_O_CPF_OU_CNPJ_INFORMADO
						, givenPersonDTO.getCpfCnpj()));
    }
	
	@Test
	@DisplayName("3.3. Quando criar pessoa com Email existente, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenCreatePersonWithExistingEmailShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
        when(personRepository.existsByEmailIgnoreCase(anyString())).thenReturn(Boolean.TRUE);
        
        //then
		assertThrowsExceptionWithCorrectMessage(() -> personService.create(givenPersonDTO)
				, DataIntegrityViolationException.class
				, String.format(JA_EXISTE_CADASTRO_COM_O_E_MAIL_INFORMADO
						, givenPersonDTO.getEmail()));
    }

	@Test
	@DisplayName("4. Quando atualizar pessoa existente, deve retornar QueryPersonDTO")
    void whenUpdateExistingPersonShouldReturnQueryPersonDTO() {
		//when
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        
        //then
        QueryPersonDTO result = personService.update(ID, givenPersonDTO);
        assertAll(
        		"Check if user updated and QueryUserDTO was returned"
        		, () -> verify(personRepository,times(1)).save(any(Person.class))
        		, () -> assertQueryPersonDTO(result)
        		, () -> assertEquals(EMAIL, result.getUsername())
        );
    }
	
	@Test
	@DisplayName("5. Quando excluir pessoa com id existente e sem relação com cliente ou veterinário, deve ser excluído")
    void whenDeletePersonWithExistingIdShouldBeDeleted() {
		//given
		person.setClient(null);
		person.setVet(null);
		
		//when
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        
        //then
        personService.deleteById(ID);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(personRepository, times(1)).findById(anyLong())
            	,() -> verify(personRepository, times(1)).delete(any(Person.class))
            	,() -> verify(userService, times(1)).removeRoleToUser(person.getUser().getId(), ROLE_PERSON)
        );
        
    }
	
	@Test
	@DisplayName("5.2. Quando tentar excluir pessoa com vínculo de cliente, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeletePersonWithClientRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		person.setVet(null);
		
		//when
		when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> personService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_CLIENTE
						, person.getId()
						, person.getClient().getId()));        
    }
	
	@Test
	@DisplayName("5.3. Quando tentar excluir pessoa com vínculo de veterinário, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeletePersonWithVetRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		person.setClient(null);
		
		//when
		when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> personService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(
						PESSOA_NAO_PODE_SER_EXCLUIDA_POR_RELACAO_COM_VETERINARIO
						, person.getId()
						, person.getVet().getId()));          
    }
	
	@Test
	@DisplayName("6. Deve criar usuário para pessoa com ROLE_PERSON")
    void shouldCreateUserToPersonWithROLE_PERSON() {
		//given
		RecordUserDTO recordUserDTO = new RecordUserDTO(EMAIL, CPF_CNPJ, List.of(ROLE_PERSON));
		User newUser = new User(ID, EMAIL, CPF_CNPJ, new HashSet<>(), null, new ArrayList<>());
		
		//when
        when(userService.create(recordUserDTO, ENTITY)).thenReturn(new EntityDtoBox<>(newUser, ENTITY));
        
        //then
        User result = personService.createUserForPerson(EMAIL, CPF_CNPJ);
        assertEquals(EMAIL,	result.getUsername());
    }
	
	@Test
	@DisplayName("6.2. Deve criar usuário mesmo que já existe usuário com o nome do e-mail da pessoa")
    void shouldCreateUserEvenThereIsAlreadyUserWithTheUsernameWithPersonEmail() {
		//given
		RecordUserDTO recordUserDTO = new RecordUserDTO(EMAIL, CPF_CNPJ, List.of(ROLE_PERSON));
		RecordUserDTO newRecordUserDTO = new RecordUserDTO(NEW_EMAIL, CPF_CNPJ, List.of(ROLE_PERSON));
		User newUser = new User(ID, NEW_EMAIL, CPF_CNPJ, new HashSet<>(), null,new ArrayList<>());
		person.setUser(newUser);
		
		//when
        when(userService.create(recordUserDTO, ENTITY)).thenThrow(DataIntegrityViolationException.class);
        when(userService.create(newRecordUserDTO, ENTITY)).thenReturn(new EntityDtoBox<>(newUser, ENTITY));
        
        //then
        User result = personService.createUserForPerson(EMAIL, CPF_CNPJ);
        assertEquals(NEW_EMAIL,	result.getUsername());
    }
	
	@Test
	@DisplayName("7. Deve trocar usuário, por id, da pessoa")
    void shouldChangeUserByIdofPerson() {
		//given
		User newUser = new User(ID_NEW, NEW_EMAIL, CPF_CNPJ, new HashSet<>(), null,new ArrayList<>());
		
		//when
		doReturn(new EntityDtoBox<>(person, ENTITY)).when(personService).findById(ID, ENTITY);
        when(personRepository.existsByUserId(anyLong())).thenReturn(Boolean.FALSE);
        when(userService.findById(ID_NEW, ENTITY)).thenReturn(new EntityDtoBox<>(newUser, ENTITY));
		doNothing().when(userService).removeRoleToUser(ID, ROLE_PERSON);
		doNothing().when(userService).addRoleToUser(ID_NEW, ROLE_PERSON);
        
		
        //then
        personService.handleUser(ID, ID_NEW);
        assertAll("verify if personRepository.save, userService.removeRoleToUser and userService.addRoleToUser runs"
        		, ()-> verify(personRepository, times(1)).save(any(Person.class))
        		, ()-> verify(userService).removeRoleToUser(ID, ROLE_PERSON)
        		, ()-> verify(userService).addRoleToUser(ID_NEW, ROLE_PERSON));
    }
	
	@Test
	@DisplayName("7.2. Quando tentar trocar usuário e ele já está vinculado, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryHandleUserAndAlreadsLinkedShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		doReturn(new EntityDtoBox<>(person, ENTITY)).when(personService).findById(ID, ENTITY);
		
        //then
        assertThrowsExceptionWithCorrectMessage(
        		() -> personService.handleUser(ID, ID)
        		, DataIntegrityViolationException.class
        		, String.format(PESSOA_JA_ESTA_VINCULADA_COM_O_USUARIO
						, ID, ID));
    }
	
	@Test
	@DisplayName("7.3. Quando tentar trocar usuário e ele já está vinculado em outra pessoa, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryHandleUserAndAlreadsLinkedInAnotherPersonShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		doReturn(new EntityDtoBox<>(person, ENTITY)).when(personService).findById(ID, ENTITY);
		when(personRepository.existsByUserId(anyLong())).thenReturn(Boolean.TRUE);
		
        //then
        assertThrowsExceptionWithCorrectMessage(
        		() -> personService.handleUser(ID, ID_NEW)
        		, DataIntegrityViolationException.class
        		, String.format(USUARIO_JA_VINCULADO_COM_OUTRA_PESSOA
						, ID_NEW));
    }
	
	@Test
	@DisplayName("8. Deve trocar usuário, por nome de usuário, da pessoa")
    void shouldChangeUserByUsernameofPerson() {
		//when
		when(userService.findByUsername(person.getUser().getUsername())).thenReturn(person.getUser());
		doNothing().when(personService).handleUser(ID, ID);
        
		
        //then
        personService.handleUser(ID, person.getUser().getUsername());
        verify(personService).handleUser(ID, person.getUser().getUsername());
    }
}
