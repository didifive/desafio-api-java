package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_CLIENT;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_CACHORRO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_CLIENTE_PARA_PESSOA_INFORMADA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.ClientUtils.DOG;
import static com.gft.meuamigau.utils.ClientUtils.ID;
import static com.gft.meuamigau.utils.ClientUtils.PERSON;
import static com.gft.meuamigau.utils.ClientUtils.REGISTRATION_DATE_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.opentest4j.MultipleFailuresError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.ClientRepository;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.utils.ClientUtils;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> ClientService")
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
	
	private static final long NEW_ID = 3L;
	private Client client;
	private RecordClientDTO givenClientDTO;
	
	private static final Long INVALID_ID = 2L;
	
	@Mock
    private ClientRepository clientRepository;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private UserService userService;
	
	@Spy
	@InjectMocks 
    private ClientService clientService;
	
	@BeforeEach
	private void setup() {
		client = ClientUtils.createFakeEntity();
		givenClientDTO = ClientUtils.createFakeRecordClientDTO();
	}
	
	private void assertQueryClientDTO(QueryClientDTO queryClientDTO) throws MultipleFailuresError {
		assertAll(
        		"Check if QueryClientDTO was returned"
        		, () -> assertNotNull(queryClientDTO)
        		, () -> assertEquals(QueryClientDTO.class, 					queryClientDTO.getClass())
        		, () -> assertEquals(ID, 									queryClientDTO.getId())
        		, () -> assertEquals(REGISTRATION_DATE_STRING, 				queryClientDTO.getRegistrationDate())
        		, () -> assertEquals(PersonMapper.toAbstractDTO(PERSON),	queryClientDTO.getPerson())
        		, () -> assertEquals(List.of(DogMapper.toAbstractDTO(DOG)),	queryClientDTO.getDogs())
        );
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar QueryClientDTO")
    void whenFindByIdThenShouldReturnQueryClientDTO() {
		//when
        when(clientRepository.findById(ID)).thenReturn(Optional.of(client));
        
        //then
        assertQueryClientDTO(clientService.findById(ID).getDto());
    }

	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidNameThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> clientService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(CLIENTE_NAO_ENCONTRADO_BY_ID
						,INVALID_ID));    
	}

	
	@Test
	@DisplayName("2. Quando procurar todos os clientes, deve retornar paginação de QueryClientDTO")
    void whenSearchingForAllClientsShouldReturnPaginationOfQueryClientDTO() {
		//given
		Page<Client> clientPage = new PageImpl<>(List.of(client));
		Pageable pageable = PageRequest.of(0, 8);
		QueryClientDTO expectedClientDTO = ClientUtils.createFakeQueryClientDTO();
		
		//when
        when(clientRepository.findAll(any(Pageable.class))).thenReturn(clientPage);
        
        //then
        Page<QueryClientDTO> result = clientService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryClientDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryClientDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedClientDTO)
        );
    }
	
	@Test
	@DisplayName("3. Quando criar cliente, deve retornar QueryClientDTO")
    void whenCreateClientShouldReturnQueryClientDTO() {
		//given
		Client newClient = ClientMapper.toEntity(givenClientDTO);
		
		//when
		doNothing().when(clientService).validatePerson(newClient, givenClientDTO.getPersonId());
		doNothing().when(clientService).addRoleClientInUser(newClient);
		when(clientRepository.save(newClient)).thenReturn(client);
        
        //then
        QueryClientDTO result = clientService.create(givenClientDTO);
        assertAll(
        		"Check if user saved and QueryUserDTO was returned"
        		, () -> assertQueryClientDTO(result)
        );
    }
	
	@Test
	@DisplayName("3.2. Quando criar cliente sem data de registro informada, deve gerar data com dia atual")
    void whenCreateClientWithoutInformedRegistrationDateShouldGenerenateNewDate() {
		//given
		Client newClient = ClientMapper.toEntity(givenClientDTO);
		givenClientDTO.setRegistrationDate(null);
		newClient.setRegistrationDate(DateTimeConverter.dataAtual());
		
		//when
		doNothing().when(clientService).validatePerson(newClient, givenClientDTO.getPersonId());
		doNothing().when(clientService).addRoleClientInUser(newClient);
		when(clientRepository.save(newClient)).thenReturn(client);
        
        //then
        QueryClientDTO result = clientService.create(givenClientDTO);
        assertAll(
        		"Check if user saved and QueryUserDTO was returned"
        		, () -> assertQueryClientDTO(result)
        );
    }
	
	@Test
	@DisplayName("4. Deve verificar se pessoa já não está vinculada com outro cliente")
    void shouldVerifyIfPersonExistsInAnotherClient() {
		//when
		when(clientRepository.existsByPersonId(ID)).thenReturn(Boolean.FALSE);
		doNothing().when(clientService).setPersonInClient(client, ID);
        
        //then
		clientService.validatePerson(client, ID);
		verify(clientService, times(1)).setPersonInClient(client, ID);
    }
	
	@Test
	@DisplayName("4.2. quando pessoa informada já está vinculada, deve lançar DataIntegrityViolationException com menssagem correta")
    void whenTryIncludeInvalidPersonInClientshouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		when(clientRepository.existsByPersonId(INVALID_ID)).thenReturn(Boolean.TRUE);
        
        //then
		assertThrowsExceptionWithCorrectMessage(
				() -> clientService.validatePerson(client, INVALID_ID)
				, DataIntegrityViolationException.class
				, String.format(JA_EXISTE_CLIENTE_PARA_PESSOA_INFORMADA
						, INVALID_ID));
    }
	
	@Test
	@DisplayName("5. Deve incluir pessoa no cliente")
    void shouldIncludePersonInClient() {
		//given
		Person person = client.getPerson();
		client.setPerson(null);
		
		//when
		when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        
        //then
		clientService.setPersonInClient(client, person.getId());
		assertEquals(person, client.getPerson());
    }
	
	@Test
	@DisplayName("5.2. quando tentar incluir pessoa inexistente no cliente, deve lançar EntityNotFoundException com mensagem correta")
    void whenTryIncludeInvalidPersonInClientshouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        //then
		assertThrowsExceptionWithCorrectMessage(
				() -> clientService.setPersonInClient(client, INVALID_ID)
				, EntityNotFoundException.class
				, String.format(PESSOA_NAO_ENCONTRADA_BY_ID
						, INVALID_ID));
    }
	
	@Test
	@DisplayName("6. deve incluir perfil ROLE_CLIENT no usuário")
    void shouldIncludeROLE_CLIENTInUser() {
		//when
		doNothing().when(userService).addRoleToUser(ID,ROLE_CLIENT);
        
        //then
		clientService.addRoleClientInUser(client);
		verify(userService, times(1)).addRoleToUser(ID, ROLE_CLIENT);
    }

	@Test
	@DisplayName("7. Quando atualizar cliente existente, deve retornar QueryClientDTO")
    void whenUpdateExistingClientShouldReturnQueryClientDTO() {
		//when
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        
        //then
        QueryClientDTO result = clientService.update(ID, givenClientDTO);
        assertAll(
        		"Check if user updated and QueryUserDTO was returned"
        		, () -> verify(clientRepository,times(1)).save(any(Client.class))
        		, () -> assertQueryClientDTO(result)
        );
    }
	
	@Test
	@DisplayName("7.3. Quando atualizar cliente com pessoa diferente, deve rodar o método validatePerson")
    void whenUpdateClientWithDifferentPersonShouldRunValidadePersonMethod() {
		//given
		givenClientDTO.setPersonId(NEW_ID);
		
		//when
		when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
		doNothing().when(clientService).validatePerson(client, NEW_ID);
		when(clientRepository.save(any(Client.class))).thenReturn(client);
		
		//then
        clientService.update(ID, givenClientDTO);
        verify(clientService, times(1)).validatePerson(client, NEW_ID);
    }
	
	
	@Test
	@DisplayName("8. Quando excluir cliente com id existente e sem relação com cachorro ou atendimento, deve ser excluído")
    void whenDeleteClientWithExistingIdShouldBeDeleted() {
		//given
		client.setDogs(new ArrayList<>());
		client.setAttendances(new ArrayList<>());
		
		//when
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        
        //then
        clientService.deleteById(ID);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(clientRepository, times(1)).findById(anyLong())
            	,() -> verify(clientRepository, times(1)).deleteById(anyLong())
            	,() -> verify(userService, times(1)).removeRoleToUser(client.getPerson().getUser().getId(), ROLE_CLIENT)
        );
        
    }
	
	@Test
	@DisplayName("8.2. Quando tentar excluir cliente com vínculo com cachorro, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteClientWithClientRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		client.setAttendances(new ArrayList<>());
		
		//when
		doReturn(new EntityDtoBox<>(client, ENTITY)).when(clientService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> clientService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_CACHORRO
						, client.getId()
						, client.getDogs().stream()
							.map(dog -> dog.getId().toString())
							.collect(Collectors.joining(","))));        
    }
	
	@Test
	@DisplayName("8.3. Quando tentar excluir cliente com vínculo de atendimento, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteClientWithAttendanceRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		client.setDogs(new ArrayList<>());
		
		//when
		doReturn(new EntityDtoBox<>(client, ENTITY)).when(clientService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> clientService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(CLIENTE_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
						, client.getId()
						, client.getAttendances().stream()
							.map(attendance -> attendance.getId().toString())
							.collect(Collectors.joining(","))));           
    }

}