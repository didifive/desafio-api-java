package com.gft.meuamigau.dtos.person;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.gft.meuamigau.entities.Person;
import com.gft.meuamigau.utils.PersonUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> PersonMapper")
class PersonMapperTest {
	
	private Person person;
	private RecordPersonDTO recordPersonDTO;
	private QueryPersonDTO queryPersonDTO;
	private DetailsPersonDTO detailsPersonDTO;
	private AbstractPersonDTO abstractPersonDTO;
		
	@BeforeEach
	private void setup() {
		person = PersonUtils.createFakeEntity();
		recordPersonDTO = PersonUtils.createFakeRecordPersonDTO();
		queryPersonDTO = PersonUtils.createFakeQueryPersonDTO();
		detailsPersonDTO = PersonUtils.createFakeDetailsPersonDTO();
		abstractPersonDTO = PersonUtils.createFakeAbstractPersonDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<PersonMapper> constructor = PersonMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordPersonDTO para Person")
	void shouldConvertRecordPersonDTOToPerson()throws Exception {
		//then
		Person result = PersonMapper.toEntity(recordPersonDTO);
		assertAll("Assert who expected Person is returned"
				,() -> assertEquals(person.getName(), 		result.getName())
				,() -> assertEquals(person.getPersonType(),	result.getPersonType())
				,() -> assertEquals(person.getCpfCnpj(), 	result.getCpfCnpj())
				,() -> assertEquals(person.getBirthDate(), 	result.getBirthDate())
				,() -> assertEquals(person.getPhone(), 		result.getPhone())
				,() -> assertEquals(person.getEmail(), 		result.getEmail())
				,() -> assertThat(person.getAddresses()).containsAll(result.getAddresses())
				,() -> assertEquals(person.getAddresses().size(), result.getAddresses().size())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Person para QueryPersonDTO")
	void shouldConvertPersonToQueryPersonDTO()throws Exception {
		//then
		QueryPersonDTO result = PersonMapper.toQueryDTO(person);
		assertAll("Assert who expected QueryPersonDTO is returned"
				,() -> assertEquals(queryPersonDTO.getId(),   		result.getId())
				,() -> assertEquals(queryPersonDTO.getName(), 		result.getName())
				,() -> assertEquals(queryPersonDTO.getPersonType(),	result.getPersonType())
				,() -> assertEquals(queryPersonDTO.getCpfCnpj(), 	result.getCpfCnpj())
				,() -> assertEquals(queryPersonDTO.getBirthDate(), 	result.getBirthDate())
				,() -> assertEquals(queryPersonDTO.getPhone(), 		result.getPhone())
				,() -> assertEquals(queryPersonDTO.getEmail(), 		result.getEmail())
				,() -> assertEquals(queryPersonDTO.getUsername(), 	result.getUsername())
				,() -> assertThat(queryPersonDTO.getAddresses()).containsAll(result.getAddresses())
				,() -> assertEquals(queryPersonDTO.getAddresses().size(), result.getAddresses().size())
		);
	}
	
	@Test
	@DisplayName("3. Deve converter Person para DetailsPersonDTO")
	void shouldConvertPersonToDetailsPersonDTO()throws Exception {
		//then
		DetailsPersonDTO result = PersonMapper.toDetailsDTO(person);
		assertAll("Assert who expected DetaisPersonDTO is returned"
				,() -> assertEquals(detailsPersonDTO.getId(),   		result.getId())
				,() -> assertEquals(detailsPersonDTO.getName(), 		result.getName())
				,() -> assertEquals(detailsPersonDTO.getPersonType(),	result.getPersonType())
				,() -> assertEquals(detailsPersonDTO.getCpfCnpj(), 		result.getCpfCnpj())
				,() -> assertEquals(detailsPersonDTO.getBirthDate(), 	result.getBirthDate())
				,() -> assertEquals(detailsPersonDTO.getPhone(), 		result.getPhone())
				,() -> assertEquals(detailsPersonDTO.getEmail(), 		result.getEmail())
				,() -> assertEquals(detailsPersonDTO.getUsername(), 	result.getUsername())
				,() -> assertEquals(detailsPersonDTO.getClient(), 		result.getClient())
				,() -> assertEquals(detailsPersonDTO.getVet(), 			result.getVet())
				,() -> assertThat(detailsPersonDTO.getAddresses()).containsAll(result.getAddresses())
				,() -> assertEquals(detailsPersonDTO.getAddresses().size(), result.getAddresses().size())
		);
	}
	
	@Test
	@DisplayName("4. Deve converter Person para AbstractPersonDTO")
	void shouldConvertPersonToAbstractPersonDTO()throws Exception {
		//then
		AbstractPersonDTO result = PersonMapper.toAbstractDTO(person);
		assertAll("Assert who expected DetaisPersonDTO is returned"
				,() -> assertEquals(abstractPersonDTO.getId(),   		result.getId())
				,() -> assertEquals(abstractPersonDTO.getName(), 		result.getName())
				,() -> assertEquals(abstractPersonDTO.getPersonType(),	result.getPersonType())
		);
	}

}
