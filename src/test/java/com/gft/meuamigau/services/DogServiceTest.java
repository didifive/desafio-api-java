package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CACHORRO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.CACHORRO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.DogUtils.BREED;
import static com.gft.meuamigau.utils.DogUtils.CLIENT;
import static com.gft.meuamigau.utils.DogUtils.COLOR;
import static com.gft.meuamigau.utils.DogUtils.DATE_STRING;
import static com.gft.meuamigau.utils.DogUtils.ID;
import static com.gft.meuamigau.utils.DogUtils.NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.gft.meuamigau.dtos.breed.BreedMapper;
import com.gft.meuamigau.dtos.client.ClientMapper;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.DogRepository;
import com.gft.meuamigau.utils.DogUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> DogService")
@ExtendWith(MockitoExtension.class)
class DogServiceTest {
	
	private Dog dog;
	private RecordDogDTO givenDogDTO;
	
	private static final Long INVALID_ID = 2L;
	
	@Mock
    private DogRepository dogRepository;
	
	@Mock
	private ClientService clientService;
	
	@Mock
	private BreedService breedService;
	
	@Spy
	@InjectMocks 
    private DogService dogService;
	
	@BeforeEach
	private void setup() {
		dog = DogUtils.createFakeEntity();
		givenDogDTO = DogUtils.createFakeRecordDogDTO();
	}
	
	private void assertQueryDogDTO(QueryDogDTO queryDogDTO) throws MultipleFailuresError {
		assertAll(
        		"Check if QueryClientDTO was returned"
        		, () -> assertNotNull(queryDogDTO)
        		, () -> assertEquals(QueryDogDTO.class, 					queryDogDTO.getClass())
        		, () -> assertEquals(ID, 									queryDogDTO.getId())
        		, () -> assertEquals(NAME,									queryDogDTO.getName())
        		, () -> assertEquals(DATE_STRING, 							queryDogDTO.getRegistrationDate())
        		, () -> assertEquals(DATE_STRING,							queryDogDTO.getBirthDate())
        		, () -> assertEquals(COLOR,									queryDogDTO.getColor())
        		, () -> assertEquals(BreedMapper.toQueryDTO(BREED),			queryDogDTO.getBreed())
        		, () -> assertEquals(ClientMapper.toAbstractDTO(CLIENT),	queryDogDTO.getClient())
        );
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar QueryDogDTO")
    void whenFindByIdThenShouldReturnQueryDogDTO() {
		//when
        when(dogRepository.findById(ID)).thenReturn(Optional.of(dog));
        
        //then
        assertQueryDogDTO(dogService.findById(ID).getDto());
    }

	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidIdThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> dogService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(CACHORRO_NAO_ENCONTRADO_BY_ID
						,INVALID_ID));    
	}

	
	@Test
	@DisplayName("2. Quando procurar todos os cachorros, deve retornar paginação de QueryClientDTO")
    void whenSearchingForAllClientsShouldReturnPaginationOfQueryClientDTO() {
		//given
		Page<Dog> clientDog = new PageImpl<>(List.of(dog));
		Pageable pageable = PageRequest.of(0, 8);
		QueryDogDTO expectedDogDTO = DogUtils.createFakeQueryDogDTO();
		
		//when
        when(dogRepository.findAll(pageable)).thenReturn(clientDog);
        
        //then
        Page<QueryDogDTO> result = dogService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryDogDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryDogDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedDogDTO)
        );
    }
	
	@Test
	@DisplayName("2.2 Deve procurar todos os cachorros por cliente")
    void shouldFindAllDogsForClient() {
		//given
		Page<Dog> clientDog = new PageImpl<>(List.of(dog));
		Pageable pageable = PageRequest.of(0, 8);
		QueryDogDTO expectedDogDTO = DogUtils.createFakeQueryDogDTO();
		
		//when
        when(dogRepository.findByClientId(ID, pageable)).thenReturn(clientDog);
        
        //then
        Page<QueryDogDTO> result = dogService.findAllByClientId(ID, pageable);
        assertAll(
        		"Check if Page<QueryDogDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryDogDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedDogDTO)
        );
    }
	
	@Test
	@DisplayName("3. Quando criar cachorro, deve retornar QueryClientDTO")
    void whenCreateDogShouldReturnQueryClientDTO() {
		//when
		when(dogRepository.save(any(Dog.class))).thenReturn(dog);
		when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getClient(), ENTITY));
        when(breedService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getBreed(), ENTITY));
        
        //then
        QueryDogDTO result = dogService.create(givenDogDTO);
        assertAll(
        		"Check if dog saved and QueryDogDTO was returned"
        		, () -> assertQueryDogDTO(result)
        );
    }
	
	@Test
	@DisplayName("3.2. Quando criar cachorro sem data de registro informada, deve gerar data com dia atual")
    void whenCreateClientWithoutInformedRegistrationDateShouldGenerenateNewDate() {
		//given
		givenDogDTO.setRegistrationDate(null);
		
		//when
		when(dogRepository.save(any(Dog.class))).thenReturn(dog);
        when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getClient(), ENTITY));
        when(breedService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getBreed(), ENTITY));
        
        //then
        QueryDogDTO result = dogService.create(givenDogDTO);
        assertAll(
        		"Check if dog saved and QueryDogDTO was returned"
        		, () -> assertQueryDogDTO(result)
        );
    }

	@Test
	@DisplayName("4. Quando atualizar cachorro existente, deve retornar QueryDogDTO")
    void whenUpdateExistingDogShouldReturnQueryDogDTO() {
		//when
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getClient(), ENTITY));
        when(breedService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getBreed(), ENTITY));
        when(dogRepository.save(any(Dog.class))).thenReturn(dog);
        
        //then
        QueryDogDTO result = dogService.update(ID, givenDogDTO);
        assertAll(
        		"Check if dog updated and QueryDogDTO was returned"
        		, () -> verify(dogRepository,times(1)).save(any(Dog.class))
        		, () -> assertQueryDogDTO(result)
        );
    }
	
	@Test
	@DisplayName("4.2. Quando atualizar cachorro existente sem data de registro informada no RecordDogDTO, deve retornar QueryDogDTO")
    void whenUpdateExistingDogWithoutInformedRegistrationDateInRecordDogDTOShouldReturnQueryDogDTO() {
		//given
		givenDogDTO.setRegistrationDate(null);
		
		//when
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(clientService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getClient(), ENTITY));
        when(breedService.findById(ID, ENTITY)).thenReturn(new EntityDtoBox<>(dog.getBreed(), ENTITY));
        when(dogRepository.save(any(Dog.class))).thenReturn(dog);
        
        //then
        QueryDogDTO result = dogService.update(ID, givenDogDTO);
        assertAll(
        		"Check if dog updated and QueryDogDTO was returned"
        		, () -> verify(dogRepository,times(1)).save(any(Dog.class))
        		, () -> assertQueryDogDTO(result)
        );
    }
	
	@Test
	@DisplayName("5. Quando excluir cachorro com id existente e sem relação com atendimento, deve ser excluído")
    void whenDeleteDogWithExistingIdShouldBeDeleted() {
		//given
		dog.setAttendances(new ArrayList<>());
		
		//when
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        
        //then
        dogService.deleteById(ID);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(dogRepository, times(1)).findById(anyLong())
            	,() -> verify(dogRepository, times(1)).deleteById(anyLong())
        );
        
    }
	
	@Test
	@DisplayName("5.2. Quando tentar excluir cachorro com vínculo de atendimento, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteDogWithAttendanceRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		doReturn(new EntityDtoBox<>(dog, ENTITY)).when(dogService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> dogService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(CACHORRO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_ATENDIMENTO
						, dog.getId()
						, dog.getAttendances().stream()
							.map(attendance -> attendance.getId().toString())
							.collect(Collectors.joining(","))));           
    }

}