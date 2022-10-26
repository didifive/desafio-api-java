package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.dtos.breed.RequestBreedDTO;
import com.gft.meuamigau.entities.Breed;
import com.gft.meuamigau.repositories.BreedRepository;
import com.gft.meuamigau.utils.BreedUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> BreedService")
@ExtendWith(MockitoExtension.class)
class BreedServiceTest {
	
	private static final long ID = 1L;

	private static final String X_API_KEY = "xapikey";
	
	private Breed breed;
	private RequestBreedDTO requestBreedDTO;
	private QueryBreedDTO expectedBreedDTO;
	
	@Mock
	BreedRepository breedRepository;
	
	@Spy
	@InjectMocks 
    private BreedService breedService;
	
	@BeforeEach
	private void setup() {
		breed = BreedUtils.createFakeEntity();
		requestBreedDTO = BreedUtils.createFakeRequestBreedDTO();
		expectedBreedDTO = BreedUtils.createFakeQueryBreedDTO();
		ReflectionTestUtils.setField(breedService
				, "theDogApiKey"
				, X_API_KEY);
	}
	
	@Test
	@DisplayName("1. Quando procurar lista de raças em TheDogApi deve retornar lista de RequestBreedDTO")
    void whenSearchingListOfBreedsInTheDogApiShouldReturnListOfRequestBreedDTO() throws IOException, InterruptedException {		
        //then
        List<RequestBreedDTO> result = breedService.getBreedsList();
        assertAll(
        		"Check if List<RequestBreedDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(List.class, result)
        		, () -> assertInstanceOf(RequestBreedDTO.class, result.get(0))
        		, () -> assertThat(result).contains(requestBreedDTO)
        );
    }
	
	@Test
	@DisplayName("2. Quando procurar todas as raças, deve retornar paginação de QueryBreedDTO")
    void whenSearchingForAllBreedsShouldReturnPaginationOfQueryBreedDTO() {
		//given
		Pageable pageable = PageRequest.of(0, 8);
		
		//when
        doReturn(List.of(requestBreedDTO)).when(breedService).getBreedsList();
        
        //then
        Page<QueryBreedDTO> result = breedService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryBreedDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryBreedDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedBreedDTO)
        );
    }
	
	@Test
	@DisplayName("3. Quando procurar raças por nome, deve retornar paginação de QueryBreedDTO")
    void whenSearchingForBreedsByNameShouldReturnPaginationOfQueryBreedDTO() throws IOException, InterruptedException {
		//given
		Pageable pageable = PageRequest.of(0, 8);
		String name = "pin";
	
        //then
        Page<QueryBreedDTO> result = breedService.findByName(name, pageable);
        assertAll(
        		"Check if Page<QueryBreedDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryBreedDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedBreedDTO)
        );
    }
	
	@Test
	@DisplayName("4. Quando procurar imagem, deve retornar ImageBreedDTO")
    void whenSearchingForBreedImageShouldReturnImageBreedDTO() throws IOException, InterruptedException {
		//given
		String imageId = "BJa4kxc4X";
		ImageBreedDTO expectedImageBreedDTO = BreedUtils.createFakeImageBreedDTO();
       
        //then
        ImageBreedDTO result = breedService.findImageById(imageId);
        assertAll(
        		"Check if ImageBreedDTO was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(ImageBreedDTO.class, result)
        		, () -> assertEquals(expectedImageBreedDTO, result)
        );
    }
	
	@Test
	@DisplayName("5. Deve retornar raça por id na The Dog Api")
    void shouldReturnBreedByIdInTheDogApi() throws IOException, InterruptedException {
		//given
		Long id = ID;
		
		//when
        doReturn(expectedBreedDTO).when(breedService).create(requestBreedDTO);
        
        //then
        assertQueryBreedDTO(breedService.findInTheDogApiById(id));
    }
	
	@Test
	@DisplayName("6. Deve retornar raça por id no banco local")
    void shouldReturnBreedByIdInLocalDataBase() throws Exception {
		//given
		Long id = ID;
		
		//when
        when(breedRepository.findById(id)).thenReturn(Optional.of(breed));
        
        //then
        assertQueryBreedDTO(breedService.findById(id));
    }
	
	@Test
	@DisplayName("6.2. Deve retornar findInTheDogApiById se não findById não achou na base de dados local")
    void shouldReturnFindInTheDogApiByIdIfFindByIdNotFindedInLocalDataBase() throws Exception {
		//given
		Long id = ID;
		
		//when
        when(breedRepository.findById(id)).thenReturn(Optional.empty());
        doReturn(new EntityDtoBox<>(expectedBreedDTO,DTO)).when(breedService).findInTheDogApiById(id,DTO);
        
        //then
        breedService.findById(id);
        verify(breedService, times(1)).findInTheDogApiById(id,DTO);
    }
	
	@Test
	@DisplayName("6.3. Quando buscar raça por id deve retornar Breed")
    void whenFindBreedByIdshouldReturnBreed() throws Exception {
		//given
		Long id = ID;
		
		//when
        when(breedRepository.findById(id)).thenReturn(Optional.of(breed));
        
        //then
        assertBreed(breedService.findById(id, ENTITY).getEntity());
    }
	
	@Test
	@DisplayName("6.4. Quando buscar raça por findInTheDogApiById deve retornar Breed")
    void whenFindBreedByFindInTheDogApiByIdshouldReturnBreed() throws Exception {
		//given
		Long id = ID;
		
		//when
		doReturn(new EntityDtoBox<>(breed, ENTITY)).when(breedService).create(any(RequestBreedDTO.class), eq(ENTITY));
        
        //then
        assertBreed(breedService.findInTheDogApiById(id, ENTITY).getEntity());
    }
	
	@Test
	@DisplayName("7. Deve criar raça na base de dados local")
    void shouldCreateBreedInLocalDataBase() throws Exception {
		//when
        when(breedRepository.save(breed)).thenReturn(breed);
        
        //then
        assertQueryBreedDTO(breedService.create(requestBreedDTO));
    }
	
	@Test
	@DisplayName("7.2. Deve criar raça na base de dados local e retornar entidade")
    void shouldCreateBreedInLocalDataBaseAndReturnEntity() throws Exception {
		//when
        when(breedRepository.save(breed)).thenReturn(breed);
        
        //then
        assertBreed(breedService.create(requestBreedDTO,ENTITY).getEntity());
    }

	private void assertBreed(Breed result) throws MultipleFailuresError {
		assertAll("check if returns Breed"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Breed.class, result)
        		, () -> assertEquals(breed, result)
        		);
	}
	
	private void assertQueryBreedDTO(QueryBreedDTO result) throws MultipleFailuresError {
		assertAll(
        		"Check if QueryBreedDTO was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(QueryBreedDTO.class, result)
        		, () -> assertEquals(expectedBreedDTO, result)
        );
	}

}