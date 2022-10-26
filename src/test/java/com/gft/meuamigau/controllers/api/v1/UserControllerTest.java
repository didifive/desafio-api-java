package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.RoleName.ROLE_VET;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_USERS;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.URI_BASE;
import static com.gft.meuamigau.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gft.meuamigau.dtos.user.PasswordUserDTO;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.UserService;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.UserUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> UserController")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	private static final Long ID = 1L;
	private static final String USERNAME = "usuario@gft.com";
	private MockMvc mockMvc;
	private RecordUserDTO recordUserDTO;
	private QueryUserDTO queryUserDTO;
	private PasswordUserDTO passwordUserDTO;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordUserDTO = UserUtils.createFakeRecordUserDTO();
        queryUserDTO = UserUtils.createFakeQueryUserDTO();
        passwordUserDTO = new PasswordUserDTO("123456", "456789");
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar usuario e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreateUserAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(userService.create(recordUserDTO)).thenReturn(new EntityDtoBox<>(queryUserDTO, DTO));

        // then
        mockMvc.perform(post(PATH_USERS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordUserDTO)))
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryUserDTO.getId().toString())))
        				,jsonPath("$.username", is(queryUserDTO.getUsername()))
        				,jsonPath("$.rolesNames[0]", is(queryUserDTO.getRolesNames().get(0).toString()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryUserDTO.getPerson().getId().toString())))
        				,header().string("Location", URI_BASE+PATH_USERS+"/"+queryUserDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordUserDTO.setUsername(null);

        // then
        mockMvc.perform(post(PATH_USERS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordUserDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de clientes")
    void whenGETIsCalledThenReturnUsersPagedList() throws Exception {
        //given
		Page<QueryUserDTO> userPage = new PageImpl<>(List.of(queryUserDTO));
    	
    	// when
    	when(userService.findAll(any(Pageable.class))).thenReturn(userPage);

        // then
        mockMvc.perform(get(PATH_USERS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryUserDTO.getId().toString())))
        				,jsonPath("$.content[0].username", is(queryUserDTO.getUsername()))
        				,jsonPath("$.content[0].rolesNames[0]", is(queryUserDTO.getRolesNames().get(0).toString()))
        				,jsonPath("$.content[0].person.id", is(Integer.parseInt(queryUserDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyUserPagedList() throws Exception {
        //given
		Page<QueryUserDTO> clientPage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(userService.findAll(any(Pageable.class))).thenReturn(clientPage);

        // then
        mockMvc.perform(get(PATH_USERS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.totalElements", is(0))
        				,jsonPath("$.content", is(Collections.emptyList()))
        		);
    }
    
    @Test
    @DisplayName("3. Quando GET é chamado com id de usuário válido, deve retornar detalhes de usuário")
    void whenGETIsCalledWithValidUserIdShouldReturnUserDetails() throws Exception {
    	// when
    	when(userService.findById(ID)).thenReturn(new EntityDtoBox<>(queryUserDTO, DTO));

        // then
        mockMvc.perform(get(PATH_USERS+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryUserDTO.getId().toString())))
        				,jsonPath("$.username", is(queryUserDTO.getUsername()))
        				,jsonPath("$.rolesNames[0]", is(queryUserDTO.getRolesNames().get(0).toString()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryUserDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("4. Quando PUT é chamado com id de usuário válido, deve atualizar usuário e retornar detalhes")
    void whenPUTIsCalledWithValidUserIdShouldUpdateUserAndReturnDetails() throws Exception {
    	//given
    	recordUserDTO.setRolesNames(List.of(ROLE_VET));
    	queryUserDTO.setRolesNames(List.of(ROLE_VET));
    	
    	// when
    	when(userService.update(ID, recordUserDTO)).thenReturn(queryUserDTO);

        // then
        mockMvc.perform(put(PATH_USERS+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordUserDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryUserDTO.getId().toString())))
    				,jsonPath("$.username", is(queryUserDTO.getUsername()))
    				,jsonPath("$.rolesNames[0]", is(queryUserDTO.getRolesNames().get(0).toString()))
    				,jsonPath("$.person.id", is(Integer.parseInt(queryUserDTO.getPerson().getId().toString())))
    		);
    }
    
    @Test
    @DisplayName("4.2. Quando PUT é chamado com dado inválido, deve retornar Bad Request")
    void whenPUTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordUserDTO.setUsername(null);

        // then
        mockMvc.perform(put(PATH_USERS+"/"+ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordUserDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("5. Quando DELETE é chamado com id de cliente válido, deve excluir cliente e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidUserIdShouldDeleteUserAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(userService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_USERS+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(userService, times(1)).deleteById(ID);
    }
    
    @Test
    @DisplayName("6. Quando PATCH é chamado com username válido, deve fazer troca de password e retornar sem conteúdo")
    void whenPATCHIsCalledWithValidUsernameShouldHandlePasswordAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(userService).handlePassword(USERNAME, passwordUserDTO);

        // then
        mockMvc.perform(patch(PATH_USERS+"/pass/"+USERNAME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(passwordUserDTO)))
    		.andExpect(status().isNoContent());
        verify(userService, times(1)).handlePassword(USERNAME, passwordUserDTO);
    }
    
    @Test
    @DisplayName("6.2. Quando PATCH é chamado com dado inválido, deve retornar Bad Request")
    void whenPATCHIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	passwordUserDTO.setOldPassword("123");

        // then
        mockMvc.perform(patch(PATH_USERS+"/pass/"+USERNAME)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(passwordUserDTO)))
        		.andExpect(status().isBadRequest());
    }

}
