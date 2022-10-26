package com.gft.meuamigau.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import com.gft.meuamigau.dtos.auth.AuthenticationDTO;
import com.gft.meuamigau.dtos.auth.TokenDTO;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.utils.UserUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> AuthenticationService")
@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
	
	private User user;
	private AuthenticationDTO authDTO;
	String expectedToken;
	SimpleDateFormat sdf;
	
	@Mock
	private AuthenticationManager authManager;
	
	@Mock
	private Authentication authenticate;
	
	@Mock
	private Date dataExpiracao;
	
	@Spy
	@InjectMocks
	private AuthenticationService authenticationService;

	@BeforeEach
	private void setup() throws ParseException {
		user = UserUtils.createFakeEntity();
		authDTO = new AuthenticationDTO();
		authDTO.setUsername(user.getUsername());
		authDTO.setPassword(user.getPassword());
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		dataExpiracao = sdf.parse("2179-02-10 23:13:39");
		
		ReflectionTestUtils.setField(authenticationService
				, "secret"
				, "'l7zZUIndwlmFdhI,VOmDZlTrWmmZ#{xUa5f<XTApsU[A).gujL%3R;$60_&{Ht");
		ReflectionTestUtils.setField(authenticationService
				, "issuer"
				, "MeuAmigAuAPI");
		ReflectionTestUtils.setField(authenticationService
				, "expiration"
				, "10000");
		
		expectedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9."
				+ "eyJzdWIiOiIxIiwiaXNzIjoiTWV1QW1pZ0F1QVBJIiwiZXhwIjo2NTk4OTgwODE5fQ."
				+ "pDfAqWaZeetS28057YHFkUm6AxlQp81-Yq5VcfJI264";
	}
	
	@Test
	@DisplayName("1. deve gerar Token")
	void shouldGenerateToken() throws Exception {
		//when
		when(authManager.authenticate(any())).thenReturn(authenticate);
		doReturn(user).when(authenticate).getPrincipal();
		doReturn(dataExpiracao).when(authenticationService).expirationDate(any(Date.class));
		
		//then
		TokenDTO token = authenticationService.autenticar(authDTO);
		assertEquals(expectedToken, token.getToken());
		
	}
	
	@Test
	@DisplayName("2. quando token é válido, deve retornar true")
	void whenValidTokenShouldReturnTrue() throws Exception {
		//then
		assertTrue(authenticationService.verificaToken(expectedToken));
	}
	
	@Test
	@DisplayName("2.2. quando Token é nulo, deve retornar falso")
	void whenTokenIsNullShouldReturnFalse() throws Exception {
		//then
		assertFalse(authenticationService.verificaToken(null));
	}
	
	@Test
	@DisplayName("2.3. quando Token é inválido, deve retornar falso")
	void whenInvalidTokenShouldReturnFalse() throws Exception {
		//given
		String invalidToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9."
				+ "eyJzdWIiOiIxIiwiaXNzIjoiTWV1QW1pZ0F1QVBJIiwiZXhwIjo2NTk4OTgwODE5fQ."
				+ "pDfAqWaZsdfsdfssdf81-Yq5VcfJI264";
		
		//then
		assertFalse(authenticationService.verificaToken(invalidToken));
	}
	
	@Test
	@DisplayName("3. deve retornar id do usuário que gerou o token")
	void shouldReturnUserIdWhoGenerateToken() throws Exception {
		//then
		assertEquals(user.getId(), authenticationService.retornarIdUser(expectedToken));
	}
	
	@Test
	@DisplayName("4. deve retornar data com 10 segundos a mais")
	void shouldReturnDateWith10SecondsPlus() throws Exception {
		//given
		Date expectedDate = sdf.parse("2179-02-10 23:13:49");
		//then
		assertEquals(expectedDate, authenticationService.expirationDate(dataExpiracao));
	}

}
