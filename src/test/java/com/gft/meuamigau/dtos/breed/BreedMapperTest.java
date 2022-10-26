package com.gft.meuamigau.dtos.breed;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gft.meuamigau.entities.Breed;
import com.gft.meuamigau.utils.BreedUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> BreedMapper")
class BreedMapperTest {
	
	private Breed breed;
	private QueryBreedDTO queryBreedDTO;
	private RequestBreedDTO requestBreedDTO;
		
	@BeforeEach
	private void setup() {
		breed = BreedUtils.createFakeEntity();
		queryBreedDTO = BreedUtils.createFakeQueryBreedDTO();
		requestBreedDTO = BreedUtils.createFakeRequestBreedDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<BreedMapper> constructor = BreedMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter Breed para QueryBreedDTO")
	void shouldConvertBreedToQueryBreedDTO()throws Exception {
		//then
		QueryBreedDTO result = BreedMapper.toQueryDTO(breed);
		assertAll("Assert who expected QueryDogDTO is returned"
				,() -> assertEquals(queryBreedDTO.getId(),  		 		result.getId())
				,() -> assertEquals(queryBreedDTO.getName(), 				result.getName())
				,() -> assertEquals(queryBreedDTO.getLifeSpan(), 			result.getLifeSpan())
				,() -> assertEquals(queryBreedDTO.getBredFor(), 			result.getBredFor())
				,() -> assertEquals(queryBreedDTO.getBreedGroup(), 			result.getBreedGroup())
				,() -> assertEquals(queryBreedDTO.getTemperament(), 		result.getTemperament())
				,() -> assertEquals(queryBreedDTO.getReferenceImageId(),	result.getReferenceImageId())
				,() -> assertEquals(queryBreedDTO.getWeightImperial(), 		result.getWeightImperial())
				,() -> assertEquals(queryBreedDTO.getWeightMetric(), 		result.getWeightMetric())
				,() -> assertEquals(queryBreedDTO.getHeightImperial(), 		result.getHeightImperial())
				,() -> assertEquals(queryBreedDTO.getHeightMetric(), 		result.getHeightMetric())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter RequestBreedDTO para Breed")
	void shouldConvertRequestBreedDTOToBreed()throws Exception {
		//then
		Breed result = BreedMapper.toEntity(requestBreedDTO);
		assertAll("Assert who expected QueryDogDTO is returned"
				,() -> assertEquals(breed.getId(),  		 		result.getId())
				,() -> assertEquals(breed.getName(), 				result.getName())
				,() -> assertEquals(breed.getLifeSpan(), 			result.getLifeSpan())
				,() -> assertEquals(breed.getBredFor(), 			result.getBredFor())
				,() -> assertEquals(breed.getBreedGroup(), 			result.getBreedGroup())
				,() -> assertEquals(breed.getTemperament(), 		result.getTemperament())
				,() -> assertEquals(breed.getReferenceImageId(),	result.getReferenceImageId())
				,() -> assertEquals(breed.getWeightImperial(), 		result.getWeightImperial())
				,() -> assertEquals(breed.getWeightMetric(), 		result.getWeightMetric())
				,() -> assertEquals(breed.getHeightImperial(), 		result.getHeightImperial())
				,() -> assertEquals(breed.getHeightMetric(), 		result.getHeightMetric())
		);
	}
	
	@Test
	@DisplayName("3. Deve converter RequestBreedDTO para QueryBreedDTO")
	void houldConvertRequestBreedDTOToQueryBreedDTO()throws Exception {
		//then
		QueryBreedDTO result = BreedMapper.toQueryDTO(breed);
		assertAll("Assert who expected QueryDogDTO is returned"
				,() -> assertEquals(queryBreedDTO.getId(),  		 		result.getId())
				,() -> assertEquals(queryBreedDTO.getName(), 				result.getName())
				,() -> assertEquals(queryBreedDTO.getLifeSpan(), 			result.getLifeSpan())
				,() -> assertEquals(queryBreedDTO.getBredFor(), 			result.getBredFor())
				,() -> assertEquals(queryBreedDTO.getBreedGroup(), 			result.getBreedGroup())
				,() -> assertEquals(queryBreedDTO.getTemperament(), 		result.getTemperament())
				,() -> assertEquals(queryBreedDTO.getReferenceImageId(),	result.getReferenceImageId())
				,() -> assertEquals(queryBreedDTO.getWeightImperial(), 		result.getWeightImperial())
				,() -> assertEquals(queryBreedDTO.getWeightMetric(), 		result.getWeightMetric())
				,() -> assertEquals(queryBreedDTO.getHeightImperial(), 		result.getHeightImperial())
				,() -> assertEquals(queryBreedDTO.getHeightMetric(), 		result.getHeightMetric())
		);
	}

}
