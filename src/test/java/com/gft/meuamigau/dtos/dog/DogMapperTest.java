package com.gft.meuamigau.dtos.dog;

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

import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.utils.DogUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> DogMapper")
class DogMapperTest {
	
	private Dog dog;
	private RecordDogDTO recordDogDTO;
	private QueryDogDTO queryDogDTO;
	private AbstractDogDTO abstractDogDTO;
		
	@BeforeEach
	private void setup() {
		dog = DogUtils.createFakeEntity();
		recordDogDTO = DogUtils.createFakeRecordDogDTO();
		queryDogDTO = DogUtils.createFakeQueryDogDTO();
		abstractDogDTO = DogUtils.createFakeAbstractDogDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<DogMapper> constructor = DogMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordDogDTO para Dog")
	void shouldConvertRecordDogDTOToDog()throws Exception {
		//then
		Dog result = DogMapper.toEntity(recordDogDTO);
		assertAll("Assert who expected Dog is returned"
				,() -> assertEquals(dog.getName(), 				result.getName())
				,() -> assertEquals(dog.getBreed(),				result.getBreed())
				,() -> assertEquals(dog.getColor(),				result.getColor())
				,() -> assertEquals(dog.getBirthDate(), 		result.getBirthDate())
				,() -> assertEquals(dog.getRegistrationDate(), 	result.getRegistrationDate())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Dog para QueryDogDTO")
	void shouldConvertDogToQueryDogDTO()throws Exception {
		//then
		QueryDogDTO result = DogMapper.toQueryDTO(dog);
		assertAll("Assert who expected QueryDogDTO is returned"
				,() -> assertEquals(queryDogDTO.getId(),  		 		result.getId())
				,() -> assertEquals(queryDogDTO.getName(), 				result.getName())
				,() -> assertEquals(queryDogDTO.getBreed(),				result.getBreed())
				,() -> assertEquals(queryDogDTO.getColor(),				result.getColor())
				,() -> assertEquals(queryDogDTO.getBirthDate(), 		result.getBirthDate())
				,() -> assertEquals(queryDogDTO.getRegistrationDate(), 	result.getRegistrationDate())
		);
	}

	@Test
	@DisplayName("3. Deve converter Dog para AbstractDogDTO")
	void shouldConvertDogToAbstractDogDTO()throws Exception {
		//then
		AbstractDogDTO result = DogMapper.toAbstractDTO(dog);
		assertAll("Assert who expected AbstractDogDTO is returned"
				,() -> assertEquals(abstractDogDTO.getId(),   			result.getId())
				,() -> assertEquals(abstractDogDTO.getName(), 			result.getName())
				,() -> assertEquals(abstractDogDTO.getBreedName(),		result.getBreedName())
				,() -> assertEquals(abstractDogDTO.getColor(),			result.getColor())
				,() -> assertEquals(abstractDogDTO.getBirthDate(), 		result.getBirthDate())
		);
	}
}
