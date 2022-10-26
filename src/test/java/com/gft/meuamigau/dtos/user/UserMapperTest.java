package com.gft.meuamigau.dtos.user;

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

import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.utils.UserUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test DTO Mappers -> UserMapper")
class UserMapperTest {
	
	private User user;
	private RecordUserDTO recordUserDTO;
	private QueryUserDTO queryUserDTO;
		
	@BeforeEach
	private void setup() {
		user = UserUtils.createFakeEntity();
		recordUserDTO = UserUtils.createFakeRecordUserDTO();
		queryUserDTO = UserUtils.createFakeQueryUserDTO();
	}
	
	@Test
	@DisplayName("0. O construtor padrão deve estar privado")
	void defaultConstructorShouldBePrivate() throws Exception {
		Constructor<UserMapper> constructor = UserMapper.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}
	
	@Test
	@DisplayName("1. Deve converter RecordUserDTO para User")
	void shouldConvertRecordUserDTOToUser()throws Exception {
		//then
		User result = UserMapper.fromDTO(recordUserDTO);
		assertAll("Assert who expected User is returned"
				,() -> assertEquals(user.getUsername(), result.getUsername())
				,() -> assertEquals(user.getPassword(), result.getPassword())
				,() -> assertEquals(user.getRoles(), 	result.getRoles())
		);
	}
	
	@Test
	@DisplayName("2. Deve converter User para QueryUserDTO")
	void shouldConvertPersonToQueryPersonDTO()throws Exception {
		//then
		QueryUserDTO result = UserMapper.fromEntity(user);
		assertAll("Assert who expected QueryUserDTO is returned"
				,() -> assertEquals(queryUserDTO.getId(),   		result.getId())
				,() -> assertEquals(queryUserDTO.getUsername(),		result.getUsername())
				,() -> assertEquals(queryUserDTO.getRolesNames(),	result.getRolesNames())
		);
	}

}
