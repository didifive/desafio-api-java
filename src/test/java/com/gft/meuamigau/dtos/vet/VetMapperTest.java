package com.gft.meuamigau.dtos.vet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.utils.VetUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> VetMapper")
class VetMapperTest {
	
	private Vet vet;
	private RecordVetDTO recordVetDTO;
	private QueryVetDTO queryVetDTO;
	private AbstractVetDTO abstractVetDTO;
		
	@BeforeEach
	private void setup() {
		vet = VetUtils.createFakeEntity();
		recordVetDTO = VetUtils.createFakeRecordVetDTO();
		queryVetDTO = VetUtils.createFakeQueryVetDTO();
		abstractVetDTO = VetUtils.createFakeAbstractVetDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<VetMapper> constructor = VetMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordVetDTO para Vet")
	void shouldConvertRecordVetDTOToVet()throws Exception {
		//then
		Vet result = VetMapper.toEntity(recordVetDTO);
		assertAll("Assert who expected Vet is returned"
				,() -> assertEquals(null,					 	result.getId())
				,() -> assertEquals(vet.getRegistrationDate(), 	result.getRegistrationDate())
				,() -> assertEquals(vet.getSpeciality(), 		result.getSpeciality())
				,() -> assertEquals(vet.getCrmv(), 				result.getCrmv())
				,() -> assertEquals(vet.getCrmvUf(), 			result.getCrmvUf())
				,() -> assertEquals(vet.getPerson().getId(), 	result.getPerson().getId())
				,() -> assertEquals(new ArrayList<>(), 			result.getAttendances())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Vet para QueryVetDTO")
	void shouldConvertVetToQueryVetDTO()throws Exception {
		//then
		QueryVetDTO result = VetMapper.toQueryDTO(vet);
		assertAll("Assert who expected QueryVetDTO is returned"
				,() -> assertEquals(queryVetDTO.getId(),   				result.getId())
				,() -> assertEquals(queryVetDTO.getRegistrationDate(), 	result.getRegistrationDate())
				,() -> assertEquals(queryVetDTO.getSpeciality(), 		result.getSpeciality())
				,() -> assertEquals(queryVetDTO.getCrmv(),		 		result.getCrmv())
				,() -> assertEquals(queryVetDTO.getCrmvUf(),		 	result.getCrmvUf())
				,() -> assertEquals(queryVetDTO.getPerson(),		 	result.getPerson())
		);
	}
	
	@Test
	@DisplayName("3. Deve converter Vet para AbstractVetDTO")
	void shouldConvertVetToAbstractVetDTO()throws Exception {
		//then
		AbstractVetDTO result = VetMapper.toAbstractDTO(vet);
		assertAll("Assert who expected AbstractVetDTO is returned"
				,() -> assertEquals(abstractVetDTO.getId(),   				result.getId())
				,() -> assertEquals(abstractVetDTO.getRegistrationDate(), 	result.getRegistrationDate())
				,() -> assertEquals(abstractVetDTO.getSpeciality(), 		result.getSpeciality())
				,() -> assertEquals(abstractVetDTO.getCrmv(),		 		result.getCrmv())
				,() -> assertEquals(abstractVetDTO.getCrmvUf(),		 		result.getCrmvUf())
		);
	}

}
