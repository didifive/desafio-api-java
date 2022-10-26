package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_AUTH;
import static com.gft.meuamigau.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gft.meuamigau.dtos.auth.AuthenticationDTO;
import com.gft.meuamigau.dtos.auth.TokenDTO;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.AuthenticationService;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> AuthenticationController")
@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
	
	private static final String USERNAME = "admin@gft.com";
	private static final String PASSWORD = "123456";

	private static final String VALID_TOKEN = "XXXXXXXXXXXX";
	
	private MockMvc mockMvc;
	
	private TokenDTO tokenDTO = new TokenDTO();
	private AuthenticationDTO authenticationDTO = new AuthenticationDTO();

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        tokenDTO.setToken(VALID_TOKEN);
        authenticationDTO.setUsername(USERNAME);
        authenticationDTO.setPassword(PASSWORD);
    }

    @Test
    @DisplayName("1. Quando POST é chamado com usuário e senha válidos, deve criar token de autorização")
    void whenPOSTIsCalledWithValidUsernameAndPasswordShouldCreateAuthorizationToken() throws Exception {
        // when
        when(authenticationService.autenticar(any(AuthenticationDTO.class))).thenReturn(tokenDTO);

        // then
        mockMvc.perform(post(PATH_AUTH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(authenticationDTO)))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.token", is(tokenDTO.getToken()))
        		);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com usuário ou senha inválido, deve retornar UNAUTHORIZED")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        // when
        when(authenticationService.autenticar(any(AuthenticationDTO.class))).thenThrow(new BadCredentialsException(null));

    	// then
        mockMvc.perform(post(PATH_AUTH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(authenticationDTO)))
        		.andExpect(status().isUnauthorized());
    }

}
