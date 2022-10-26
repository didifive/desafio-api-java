package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_PEOPLE;
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

import com.gft.meuamigau.dtos.person.DetailsPersonDTO;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.PersonService;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.PersonUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> PersonController")
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
	
	private static final String USERNAME = "novousuario@gft.com";

	private static final String NEW_NAME = "Maria dos Santos";

	private static final String INVALID_CPF_CNPJ = "11122233344";

	private static final Long ID = 1L;
	private static final Long NEW_ID = 3L;
	
	private MockMvc mockMvc;
	private RecordPersonDTO recordPersonDTO;
	private QueryPersonDTO queryPersonDTO;
	private DetailsPersonDTO detailsPersonDTO;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordPersonDTO = PersonUtils.createFakeRecordPersonDTO();
        queryPersonDTO = PersonUtils.createFakeQueryPersonDTO();
        detailsPersonDTO = PersonUtils.createFakeDetailsPersonDTO();
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar pessoa e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreatePersonAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(personService.create(recordPersonDTO)).thenReturn(queryPersonDTO);

        // then
        mockMvc.perform(post(PATH_PEOPLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordPersonDTO)))
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryPersonDTO.getId().toString())))
        				,jsonPath("$.name", is(queryPersonDTO.getName()))
        				,jsonPath("$.personType", is(queryPersonDTO.getPersonType().toString()))
        				,jsonPath("$.cpfCnpj", is(queryPersonDTO.getCpfCnpj()))
        				,jsonPath("$.email", is(queryPersonDTO.getEmail()))
        				,jsonPath("$.birthDate", is(queryPersonDTO.getBirthDate()))
        				,jsonPath("$.phone", is(queryPersonDTO.getPhone()))
        				,jsonPath("$.username", is(queryPersonDTO.getUsername()))
        				,header().string("Location", URI_BASE+PATH_PEOPLE+"/"+queryPersonDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordPersonDTO.setCpfCnpj(INVALID_CPF_CNPJ);

        // then
        mockMvc.perform(post(PATH_PEOPLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordPersonDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de pessoas")
    void whenGETIsCalledThenReturnPeoplePagedList() throws Exception {
        //given
		Page<QueryPersonDTO> personPage = new PageImpl<>(List.of(queryPersonDTO));
    	
    	// when
    	when(personService.findAll(any(Pageable.class))).thenReturn(personPage);

        // then
        mockMvc.perform(get(PATH_PEOPLE)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryPersonDTO.getId().toString())))
        				,jsonPath("$.content[0].name", is(queryPersonDTO.getName()))
        				,jsonPath("$.content[0].personType", is(queryPersonDTO.getPersonType().toString()))
        				,jsonPath("$.content[0].cpfCnpj", is(queryPersonDTO.getCpfCnpj()))
        				,jsonPath("$.content[0].email", is(queryPersonDTO.getEmail()))
        				,jsonPath("$.content[0].birthDate", is(queryPersonDTO.getBirthDate()))
        				,jsonPath("$.content[0].phone", is(queryPersonDTO.getPhone()))
        				,jsonPath("$.content[0].username", is(queryPersonDTO.getUsername()))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyPeoplePagedList() throws Exception {
        //given
		Page<QueryPersonDTO> personPage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(personService.findAll(any(Pageable.class))).thenReturn(personPage);

        // then
        mockMvc.perform(get(PATH_PEOPLE)
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
    @DisplayName("3. Quando GET é chamado com id de pessoa válido, deve retornar detalhes da pessoa")
    void whenGETIsCalledWithValidPersonIdShouldReturnPersonDetails() throws Exception {
    	// when
    	when(personService.findById(ID)).thenReturn(new EntityDtoBox<>(detailsPersonDTO, DTO));

        // then
        mockMvc.perform(get(PATH_PEOPLE+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(detailsPersonDTO.getId().toString())))
        				,jsonPath("$.name", is(detailsPersonDTO.getName()))
        				,jsonPath("$.personType", is(detailsPersonDTO.getPersonType().toString()))
        				,jsonPath("$.cpfCnpj", is(detailsPersonDTO.getCpfCnpj()))
        				,jsonPath("$.email", is(detailsPersonDTO.getEmail()))
        				,jsonPath("$.birthDate", is(detailsPersonDTO.getBirthDate()))
        				,jsonPath("$.phone", is(detailsPersonDTO.getPhone()))
        				,jsonPath("$.username", is(detailsPersonDTO.getUsername()))
        				,jsonPath("$.client.id", is(Integer.parseInt(detailsPersonDTO.getClient().getId().toString())))
        				,jsonPath("$.vet.id", is(Integer.parseInt(detailsPersonDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("4. Quando PUT é chamado com id de pessoa válido, deve atualizar pessoa e retornar detalhes da pessoa")
    void whenPUTIsCalledWithValidPersonIdShouldUpdatePersonAndReturnPersonDetails() throws Exception {
    	//given
    	recordPersonDTO.setName(NEW_NAME);
    	queryPersonDTO.setName(NEW_NAME);
    	
    	// when
    	when(personService.update(ID, recordPersonDTO)).thenReturn(queryPersonDTO);

        // then
        mockMvc.perform(put(PATH_PEOPLE+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordPersonDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryPersonDTO.getId().toString())))
    				,jsonPath("$.name", is(queryPersonDTO.getName()))
    				,jsonPath("$.personType", is(queryPersonDTO.getPersonType().toString()))
    				,jsonPath("$.cpfCnpj", is(queryPersonDTO.getCpfCnpj()))
    				,jsonPath("$.email", is(queryPersonDTO.getEmail()))
    				,jsonPath("$.birthDate", is(queryPersonDTO.getBirthDate()))
    				,jsonPath("$.phone", is(queryPersonDTO.getPhone()))
    				,jsonPath("$.username", is(queryPersonDTO.getUsername()))
    		);
    }
    
    @Test
    @DisplayName("5. Quando DELETE é chamado com id de pessoa válido, deve excluir pessoa e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidPersonIdShouldUpdatePersonAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(personService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_PEOPLE+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(personService, times(1)).deleteById(ID);
    }
    
    @Test
    @DisplayName("6. Quando PATCH é chamado com id de pessoa e id de usuário válidos, deve trocar o usuário vinculado à pessoa e retornar sem conteúdo")
    void whenPATCHIsCalledWithValidPersonIdAndUserIdShouldUpdateUserLinkedAtPersonAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(personService).handleUser(ID, NEW_ID);

        // then
        mockMvc.perform(patch((PATH_PEOPLE+"/"+ID+"/handle-user/id/"+NEW_ID)))
    		.andExpect(status().isNoContent());
        verify(personService, times(1)).handleUser(ID, NEW_ID);
    }
    
    @Test
    @DisplayName("7. Quando PATCH é chamado com id de pessoa e nome de usuário de usuário válidos, deve trocar o usuário vinculado à pessoa e retornar sem conteúdo")
    void whenPATCHIsCalledWithValidPersonIdAndUserUsernameShouldUpdateUserLinkedAtPersonAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(personService).handleUser(ID, USERNAME);

        // then
        mockMvc.perform(patch((PATH_PEOPLE+"/"+ID+"/handle-user/username/"+USERNAME)))
    		.andExpect(status().isNoContent());
        verify(personService, times(1)).handleUser(ID, USERNAME);
    }

}
