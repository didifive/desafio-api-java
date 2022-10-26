package com.gft.meuamigau.dtos.client;

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

import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.utils.ClientUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> ClientMapper")
class ClientMapperTest {
	
	private Client client;
	private RecordClientDTO recordClientDTO;
	private QueryClientDTO queryClientDTO;
	private AbstractClientDTO abstractClientDTO;
		
	@BeforeEach
	private void setup() {
		client = ClientUtils.createFakeEntity();
		recordClientDTO = ClientUtils.createFakeRecordClientDTO();
		queryClientDTO = ClientUtils.createFakeQueryClientDTO();
		abstractClientDTO = ClientUtils.createFakeAbstractClientDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<ClientMapper> constructor = ClientMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordClientDTO para Client")
	void shouldConvertRecordClientDTOToClient()throws Exception {
		//then
		Client result = ClientMapper.toEntity(recordClientDTO);
		assertAll("Assert who expected Client is returned"
				,() -> assertEquals(client.getRegistrationDate(), 	result.getRegistrationDate())
				,() -> assertEquals(new ArrayList<>(), 				result.getDogs())
				,() -> assertEquals(new ArrayList<>(), 				result.getAttendances())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter Client para QueryClientDTO")
	void shouldConvertPersonToQueryPersonDTO()throws Exception {
		//then
		QueryClientDTO result = ClientMapper.toQueryDTO(client);
		assertAll("Assert who expected QueryClientDTO is returned"
				,() -> assertEquals(queryClientDTO.getId(),   				result.getId())
				,() -> assertEquals(queryClientDTO.getRegistrationDate(),	result.getRegistrationDate())
				,() -> assertEquals(queryClientDTO.getPerson(),				result.getPerson())
				,() -> assertEquals(queryClientDTO.getDogs(), 				result.getDogs())
		);
	}
	
	@Test
	@DisplayName("3. Deve converter Client para AbstractClientDTO")
	void shouldConvertClientToAbstractClientDTO()throws Exception {
		//then
		AbstractClientDTO result = ClientMapper.toAbstractDTO(client);
		assertAll("Assert who expected AbstractClientDTO is returned"
				,() -> assertEquals(abstractClientDTO.getId(),   				result.getId())
				,() -> assertEquals(abstractClientDTO.getRegistrationDate(), 	result.getRegistrationDate())
		);
	}

}
