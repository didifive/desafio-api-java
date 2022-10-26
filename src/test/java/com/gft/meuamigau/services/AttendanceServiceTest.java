package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.ATENDIMENTO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.AttendanceUtils.CLIENT;
import static com.gft.meuamigau.utils.AttendanceUtils.COMMENTS;
import static com.gft.meuamigau.utils.AttendanceUtils.DATE_STRING;
import static com.gft.meuamigau.utils.AttendanceUtils.DIAGNOSIS;
import static com.gft.meuamigau.utils.AttendanceUtils.DOG;
import static com.gft.meuamigau.utils.AttendanceUtils.DOG_HEIGHT;
import static com.gft.meuamigau.utils.AttendanceUtils.DOG_TEMPERAMENT;
import static com.gft.meuamigau.utils.AttendanceUtils.DOG_WEIGHT;
import static com.gft.meuamigau.utils.AttendanceUtils.ID;
import static com.gft.meuamigau.utils.AttendanceUtils.VET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.opentest4j.MultipleFailuresError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.dog.DogMapper;
import com.gft.meuamigau.dtos.vet.VetMapper;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.AttendanceRepository;
import com.gft.meuamigau.utils.AttendanceUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> AttendanceService")
@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {
	
	private Attendance attendance;
	private RecordAttendanceDTO givenAttendanceDTO;
	
	private Page<Attendance> attendancePage;
	private Pageable pageable;
	private QueryAttendanceDTO expectedAttendanceDTO;
	
	private static final Long INVALID_ID = 2L;
	
	@Mock
    private AttendanceRepository attendanceRepository;
	
	@Mock
	private ClientService clientService;
	
	@Mock
	private VetService vetService;
	
	@Mock
    private DogService dogService;
	
	@Spy
	@InjectMocks 
    private AttendanceService attendanceService;
	
	@BeforeEach
	private void setup() {
		attendance = AttendanceUtils.createFakeEntity();
		givenAttendanceDTO = AttendanceUtils.createFakeRecordAttendanceDTO();
		attendancePage = new PageImpl<>(List.of(attendance));
		pageable = PageRequest.of(0, 8);
		expectedAttendanceDTO = AttendanceUtils.createFakeQueryAttendanceDTO();
	}
	
	private void assertQueryAttendanceDTO(QueryAttendanceDTO queryAttendanceDTO) throws MultipleFailuresError {
		assertAll(
        		"Check if QueryClientDTO was returned"
        		, () -> assertNotNull(queryAttendanceDTO)
        		, () -> assertEquals(QueryAttendanceDTO.class, 				queryAttendanceDTO.getClass())
        		, () -> assertEquals(ID, 									queryAttendanceDTO.getId())
        		, () -> assertEquals(DATE_STRING, 							queryAttendanceDTO.getAttendanceDate())
        		, () -> assertEquals(DIAGNOSIS, 							queryAttendanceDTO.getDiagnosis())
        		, () -> assertEquals(COMMENTS, 								queryAttendanceDTO.getComments())
        		, () -> assertEquals(DOG_HEIGHT, 							queryAttendanceDTO.getDogHeight())
        		, () -> assertEquals(DOG_WEIGHT, 							queryAttendanceDTO.getDogWeight())
        		, () -> assertEquals(DOG_TEMPERAMENT,						queryAttendanceDTO.getTemperament())
        		, () -> assertEquals(DogMapper.toQueryDTO(DOG),				queryAttendanceDTO.getDog())
        		, () -> assertEquals(ClientMapper.toAbstractDTO(CLIENT),	queryAttendanceDTO.getClient())
        		, () -> assertEquals(VetMapper.toAbstractDTO(VET),			queryAttendanceDTO.getVet())
        );
	}
	
	private void assertAttendancePage(Page<QueryAttendanceDTO> result) throws MultipleFailuresError {
		assertAll(
        		"Check if Page<QueryAttendanceDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryAttendanceDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedAttendanceDTO)
        );
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar QueryAttendanceDTO")
    void whenFindByIdThenShouldReturnQueryAttendanceDTO() {
		//when
        when(attendanceRepository.findById(ID)).thenReturn(Optional.of(attendance));
        
        //then
        assertQueryAttendanceDTO(attendanceService.findById(ID).getDto());
    }

	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidIdThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(attendanceRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> attendanceService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(ATENDIMENTO_NAO_ENCONTRADO_BY_ID
						,INVALID_ID));    
	}

	
	@Test
	@DisplayName("2. Quando procurar todos os cachorros, deve retornar paginação de QueryAttendanceDTO")
    void whenSearchingForAllDogsShouldReturnPaginationOfQueryAttendanceDTO() {
		//when
        when(attendanceRepository.findAll(pageable)).thenReturn(attendancePage);
        
        //then
        assertAttendancePage(attendanceService.findAll(pageable));
    }
	
	@Test
	@DisplayName("2.2. Deve procurar todos os atendimentos por cliente")
    void shouldFindAllAttendancesByClient() {
		//when
        when(attendanceRepository.findByClientId(ID, pageable)).thenReturn(attendancePage);
        
        //then
        assertAttendancePage(attendanceService.findAllByClientId(ID, pageable));
    }
	
	@Test
	@DisplayName("2.3. Deve procurar todos os atendimentos por veterinário")
    void shouldFindAllAttendancesByVet() {
		//when
        when(attendanceRepository.findByVetId(ID, pageable)).thenReturn(attendancePage);
        
        //then
        assertAttendancePage(attendanceService.findAllByVetId(ID, pageable));
    }
	
	@Test
	@DisplayName("2.3. Deve procurar todos os atendimentos por cachorro")
    void shouldFindAllAttendancesByDog() {
		//when
        when(attendanceRepository.findByDogId(ID, pageable)).thenReturn(attendancePage);
        
        //then
        assertAttendancePage(attendanceService.findAllByDogId(ID, pageable));
    }
	
	@Test
	@DisplayName("3. Quando criar atendimento, deve retornar QueryAttendanceDTO")
    void whenCreateDogShouldReturnQueryAttendanceDTO() {
		//when
		when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);
		when(dogService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getDog(), ENTITY));
		when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getClient(), ENTITY));
        when(vetService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getVet(), ENTITY));
        
        //then
        assertQueryAttendanceDTO(attendanceService.create(givenAttendanceDTO));
    }
	
	@Test
	@DisplayName("3.2. Quando criar cachorro sem data de registro informada, deve gerar data com dia atual")
    void whenCreateClientWithoutInformedRegistrationDateShouldGenerenateNewDate() {
		//given
		givenAttendanceDTO.setAttendanceDate(null);
		
		//when
		when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);
		when(dogService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getDog(), ENTITY));
		when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getClient(), ENTITY));
        when(vetService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getVet(), ENTITY));
        
        //then
        assertQueryAttendanceDTO(attendanceService.create(givenAttendanceDTO));
    }

	@Test
	@DisplayName("4. Quando atualizar cachorro existente, deve retornar QueryDogDTO")
    void whenUpdateExistingDogShouldReturnQueryDogDTO() {
		//when
        when(attendanceRepository.findById(anyLong())).thenReturn(Optional.of(attendance));
		when(dogService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getDog(), ENTITY));
		when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getClient(), ENTITY));
        when(vetService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getVet(), ENTITY));
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);
        
        //then
        QueryAttendanceDTO result = attendanceService.update(ID, givenAttendanceDTO);
        assertAll(
        		"Check if dog updated and QueryDogDTO was returned"
        		, () -> verify(attendanceRepository,times(1)).save(any(Attendance.class))
        		, () -> assertQueryAttendanceDTO(result)
        );
    }
	
	@Test
	@DisplayName("4.2. Quando atualizar cachorro existente sem data de registro informada no RecordDogDTO, deve retornar QueryDogDTO")
    void whenUpdateExistingDogWithoutInformedRegistrationDateInRecordDogDTOShouldReturnQueryDogDTO() {
		//given
		givenAttendanceDTO.setAttendanceDate(null);
		
		//when
        when(attendanceRepository.findById(anyLong())).thenReturn(Optional.of(attendance));
		when(dogService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getDog(), ENTITY));
		when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getClient(), ENTITY));
        when(vetService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(attendance.getVet(), ENTITY));
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);
        
        //then
        QueryAttendanceDTO result = attendanceService.update(ID, givenAttendanceDTO);
        assertAll(
        		"Check if dog updated and QueryDogDTO was returned"
        		, () -> verify(attendanceRepository,times(1)).save(any(Attendance.class))
        		, () -> assertQueryAttendanceDTO(result)
        );
    }
	
	@Test
	@DisplayName("5. Quando excluir atendimento com id existente deve ser excluído")
    void whenDeleteAttendanceWithExistingIdShouldBeDeleted() {
		//when
        when(attendanceRepository.findById(anyLong())).thenReturn(Optional.of(attendance));
        
        //then
        attendanceService.deleteById(ID);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(attendanceRepository, times(1)).findById(anyLong())
            	,() -> verify(attendanceRepository, times(1)).deleteById(anyLong())
        );
        
    }

}