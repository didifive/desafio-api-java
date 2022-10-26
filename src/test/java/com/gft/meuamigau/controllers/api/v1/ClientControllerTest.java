package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_CLIENTS;
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

import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.entities.Client;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.ClientService;
import com.gft.meuamigau.utils.ClientUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> ClientController")
@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
	
	private static final Long ID = 1L;
	private static final String NEW_DATE = "30/05/2022";
	
	private MockMvc mockMvc;
	private RecordClientDTO recordClientDTO;
	private QueryClientDTO queryClientDTO;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordClientDTO = ClientUtils.createFakeRecordClientDTO();
        queryClientDTO = ClientUtils.createFakeQueryClientDTO();
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar cliente e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreateClientAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(clientService.create(recordClientDTO)).thenReturn(queryClientDTO);

        // then
        mockMvc.perform(post(PATH_CLIENTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordClientDTO)))
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryClientDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryClientDTO.getRegistrationDate()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryClientDTO.getPerson().getId().toString())))
        				,header().string("Location", URI_BASE+PATH_CLIENTS+"/"+queryClientDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordClientDTO.setPersonId(null);

        // then
        mockMvc.perform(post(PATH_CLIENTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordClientDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de clientes")
    void whenGETIsCalledThenReturnClientsPagedList() throws Exception {
        //given
		Page<QueryClientDTO> clientPage = new PageImpl<>(List.of(queryClientDTO));
    	
    	// when
    	when(clientService.findAll(any(Pageable.class))).thenReturn(clientPage);

        // then
        mockMvc.perform(get(PATH_CLIENTS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryClientDTO.getId().toString())))
        				,jsonPath("$.content[0].registrationDate", is(queryClientDTO.getRegistrationDate()))
        				,jsonPath("$.content[0].person.id", is(Integer.parseInt(queryClientDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyPeoplePagedList() throws Exception {
        //given
		Page<QueryClientDTO> clientPage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(clientService.findAll(any(Pageable.class))).thenReturn(clientPage);

        // then
        mockMvc.perform(get(PATH_CLIENTS)
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
    @DisplayName("3. Quando GET é chamado com id de cliente válido, deve retornar detalhes de cliente")
    void whenGETIsCalledWithValidClientIdShouldReturnClientDetails() throws Exception {
    	// when
    	when(clientService.findById(ID)).thenReturn(new EntityDtoBox<Client, QueryClientDTO>(queryClientDTO, DTO));

        // then
        mockMvc.perform(get(PATH_CLIENTS+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryClientDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryClientDTO.getRegistrationDate()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryClientDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("4. Quando PUT é chamado com id de cliente válido, deve atualizar cliente e retornar detalhes do cliente")
    void whenPUTIsCalledWithValidClientIdShouldUpdateClientAndReturnClientDetails() throws Exception {
    	//given
    	recordClientDTO.setRegistrationDate(NEW_DATE);
    	queryClientDTO.setRegistrationDate(NEW_DATE);
    	
    	// when
    	when(clientService.update(ID, recordClientDTO)).thenReturn(queryClientDTO);

        // then
        mockMvc.perform(put(PATH_CLIENTS+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordClientDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryClientDTO.getId().toString())))
    				,jsonPath("$.registrationDate", is(queryClientDTO.getRegistrationDate()))
    				,jsonPath("$.person.id", is(Integer.parseInt(queryClientDTO.getPerson().getId().toString())))
    		);
    }
    
    @Test
    @DisplayName("5. Quando DELETE é chamado com id de cliente válido, deve excluir cliente e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidClientIdShouldUpdateClientAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(clientService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_CLIENTS+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(clientService, times(1)).deleteById(ID);
    }

}
