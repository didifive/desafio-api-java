package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_VET;
import static com.gft.meuamigau.enums.constants.ErrorMessages.JA_EXISTE_VETERINARIO_PARA_PESSOA_INFORMADA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PESSOA_NAO_ENCONTRADA_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.VETERINARIO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.VETERINARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.VetUtils.CRMV;
import static com.gft.meuamigau.utils.VetUtils.CRMV_UF;
import static com.gft.meuamigau.utils.VetUtils.ID;
import static com.gft.meuamigau.utils.VetUtils.PERSON;
import static com.gft.meuamigau.utils.VetUtils.REGISTRATION_DATE_STRING;
import static com.gft.meuamigau.utils.VetUtils.SPECIALITY;
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

import com.gft.meuamigau.dtos.person.PersonMapper;
import com.gft.meuamigau.dtos.vet.QueryVetDTO;
import com.gft.meuamigau.dtos.vet.RecordVetDTO;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.PersonRepository;
import com.gft.meuamigau.repositories.VetRepository;
import com.gft.meuamigau.utils.DateTimeConverter;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.VetUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> VetService")
@ExtendWith(MockitoExtension.class)
class VetServiceTest {
	
	private static final long NEW_ID = 3L;
	private Vet vet;
	private RecordVetDTO givenVetDTO;
	
	private static final Long INVALID_ID = 2L;
	
	@Mock
    private VetRepository vetRepository;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private UserService userService;
	
	@Spy
	@InjectMocks 
    private VetService vetService;
	
	@BeforeEach
	private void setup() {
		vet = VetUtils.createFakeEntity();
		givenVetDTO = VetUtils.createFakeRecordVetDTO();
	}
	
	private void assertQueryVetDTO(QueryVetDTO queryVetDTO) throws MultipleFailuresError {
		assertAll(
        		"Check if QueryVetDTO was returned"
        		, () -> assertNotNull(queryVetDTO)
        		, () -> assertEquals(QueryVetDTO.class, 					queryVetDTO.getClass())
        		, () -> assertEquals(ID, 									queryVetDTO.getId())
        		, () -> assertEquals(REGISTRATION_DATE_STRING, 				queryVetDTO.getRegistrationDate())
        		, () -> assertEquals(SPECIALITY, 							queryVetDTO.getSpeciality())
        		, () -> assertEquals(CRMV, 									queryVetDTO.getCrmv())
        		, () -> assertEquals(CRMV_UF, 								queryVetDTO.getCrmvUf())
        		, () -> assertEquals(PersonMapper.toAbstractDTO(PERSON),	queryVetDTO.getPerson())
        );
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar QueryVetDTO")
    void whenFindByIdThenShouldReturnQueryVetDTO() {
		//when
        when(vetRepository.findById(ID)).thenReturn(Optional.of(vet));
        
        //then
        assertQueryVetDTO(vetService.findById(ID).getDto());
    }

	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidNameThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> vetService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(VETERINARIO_NAO_ENCONTRADO_BY_ID
						,INVALID_ID));    
	}

	
	@Test
	@DisplayName("2. Quando procurar todos os veterinarios, deve retornar paginação de QueryVetDTO")
    void whenSearchingForAllVetsShouldReturnPaginationOfQueryVetDTO() {
		//given
		Page<Vet> vetPage = new PageImpl<>(List.of(vet));
		Pageable pageable = PageRequest.of(0, 8);
		QueryVetDTO expectedVetDTO = VetUtils.createFakeQueryVetDTO();
		
		//when
        when(vetRepository.findAll(any(Pageable.class))).thenReturn(vetPage);
        
        //then
        Page<QueryVetDTO> result = vetService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryVetDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryVetDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedVetDTO)
        );
    }
	
	@Test
	@DisplayName("3. Quando criar veterinário, deve retornar QueryVetDTO")
    void whenCreateVetShouldReturnQueryVetDTO() {
		//given
		Vet newVet = VetMapper.toEntity(givenVetDTO);
		
		//when
		doNothing().when(vetService).validatePerson(newVet, givenVetDTO.getPersonId());
		doNothing().when(vetService).addRoleVetInUser(newVet);
		when(vetRepository.save(newVet)).thenReturn(vet);
        
        //then
        QueryVetDTO result = vetService.create(givenVetDTO);
        assertAll(
        		"Check if user saved and QueryVetDTO was returned"
        		, () -> assertQueryVetDTO(result)
        );
    }
	
	@Test
	@DisplayName("3.2. Quando criar veterinário sem data de registro informada, deve gerar data com dia atual")
    void whenCreateVetWithoutInformedRegistrationDateShouldGenerenateNewDate() {
		//given
		Vet newVet = VetMapper.toEntity(givenVetDTO);
		givenVetDTO.setRegistrationDate(null);
		newVet.setRegistrationDate(DateTimeConverter.dataAtual());
		
		//when
		doNothing().when(vetService).validatePerson(newVet, givenVetDTO.getPersonId());
		doNothing().when(vetService).addRoleVetInUser(newVet);
		when(vetRepository.save(newVet)).thenReturn(vet);
        
        //then
        QueryVetDTO result = vetService.create(givenVetDTO);
        assertAll(
        		"Check if user saved and QueryVetDTO was returned"
        		, () -> assertQueryVetDTO(result)
        );
    }
	
	@Test
	@DisplayName("4. Deve verificar se pessoa já não está vinculada com outro veterinário")
    void shouldVerifyIfPersonExistsInAnotherVet() {
		//when
		when(vetRepository.existsByPersonId(ID)).thenReturn(Boolean.FALSE);
		doNothing().when(vetService).setPersonInVet(vet, ID);
        
        //then
		vetService.validatePerson(vet, ID);
		verify(vetService, times(1)).setPersonInVet(vet, ID);
    }
	
	@Test
	@DisplayName("4.2. quando pessoa informada já está vinculada, deve lançar DataIntegrityViolationException com menssagem correta")
    void whenTryIncludeInvalidPersonInVetshouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		when(vetRepository.existsByPersonId(INVALID_ID)).thenReturn(Boolean.TRUE);
        
        //then
		assertThrowsExceptionWithCorrectMessage(
				() -> vetService.validatePerson(vet, INVALID_ID)
				, DataIntegrityViolationException.class
				, String.format(JA_EXISTE_VETERINARIO_PARA_PESSOA_INFORMADA
						, INVALID_ID));
    }
	
	@Test
	@DisplayName("5. Deve incluir pessoa no veterinário")
    void shouldIncludePersonInVet() {
		//given
		Person person = vet.getPerson();
		vet.setPerson(null);
		
		//when
		when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));
        
        //then
		vetService.setPersonInVet(vet, person.getId());
		assertEquals(person, vet.getPerson());
    }
	
	@Test
	@DisplayName("5.2. quando tentar incluir pessoa inexistente no veterinário, deve lançar EntityNotFoundException com mensagem correta")
    void whenTryIncludeInvalidPersonInVetshouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(personRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        //then
		assertThrowsExceptionWithCorrectMessage(
				() -> vetService.setPersonInVet(vet, INVALID_ID)
				, EntityNotFoundException.class
				, String.format(PESSOA_NAO_ENCONTRADA_BY_ID
						, INVALID_ID));
    }
	
	@Test
	@DisplayName("6. deve incluir perfil ROLE_VET no usuário")
    void shouldIncludeROLE_VETInPerson() {
		//when
		doNothing().when(userService).addRoleToUser(ID,ROLE_VET);
        
        //then
		vetService.addRoleVetInUser(vet);
		verify(userService, times(1)).addRoleToUser(ID, ROLE_VET);
    }

	@Test
	@DisplayName("7. Quando atualizar veterinário existente, deve retornar QueryVetDTO")
    void whenUpdateExistingVetShouldReturnQueryVetDTO() {
		//when
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));
        when(vetRepository.save(any(Vet.class))).thenReturn(vet);
        
        //then
        QueryVetDTO result = vetService.update(ID, givenVetDTO);
        assertAll(
        		"Check if user updated and QueryVetDTO was returned"
        		, () -> verify(vetRepository,times(1)).save(any(Vet.class))
        		, () -> assertQueryVetDTO(result)
        );
    }
	
	@Test
	@DisplayName("7.3. Quando atualizar veterinário com pessoa diferente, deve rodar o método validatePerson")
    void whenUpdateVetWithDifferentPersonShouldRunValidadePersonMethod() {
		//given
		givenVetDTO.setPersonId(NEW_ID);
		
		//when
		when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));
		doNothing().when(vetService).validatePerson(vet, NEW_ID);
		when(vetRepository.save(any(Vet.class))).thenReturn(vet);
		
		//then
        vetService.update(ID, givenVetDTO);
        verify(vetService, times(1)).validatePerson(vet, NEW_ID);
    }
	
	
	@Test
	@DisplayName("8. Quando excluir veterinário com id existente e sem relação com atendimento, deve ser excluído")
    void whenDeletePersonWithExistingIdShouldBeDeleted() {
		//given
		vet.setAttendances(new ArrayList<>());
		
		//when
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(vet));
        
        //then
        vetService.deleteById(ID);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(vetRepository, times(1)).findById(anyLong())
            	,() -> verify(vetRepository, times(1)).deleteById(anyLong())
        );
        
    }
	
	@Test
	@DisplayName("8.2. Quando tentar excluir veterinário com vínculo em atendimento, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteVetWithVetRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		doReturn(new EntityDtoBox<>(vet, ENTITY)).when(vetService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> vetService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(VETERINARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
						, vet.getId()
						, vet.getAttendances().stream()
							.map(attendance -> attendance.getId().toString())
							.collect(Collectors.joining(","))));           
    }

}