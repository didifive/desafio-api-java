package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_DOGS;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.URI_BASE;
import static com.gft.meuamigau.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;
import com.gft.meuamigau.entities.Dog;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.DogService;
import com.gft.meuamigau.utils.DogUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> DogController")
@ExtendWith(MockitoExtension.class)
class DogControllerTest {
	
	private static final Long ID = 1L;
	
	private MockMvc mockMvc;
	
	private RecordDogDTO recordDogDTO;
	private QueryDogDTO queryDogDTO;

    @Mock
    private DogService dogService;

    @InjectMocks
    private DogController dogController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dogController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordDogDTO = DogUtils.createFakeRecordDogDTO();
        queryDogDTO = DogUtils.createFakeQueryDogDTO();
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar cachorro e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreateDogAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(dogService.create(recordDogDTO)).thenReturn(queryDogDTO);

        // then
        mockMvc.perform(post(PATH_DOGS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordDogDTO)))
        .andDo(print())
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryDogDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryDogDTO.getRegistrationDate()))
        				,header().string("Location", URI_BASE+PATH_DOGS+"/"+queryDogDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordDogDTO.setBreedId(null);

        // then
        mockMvc.perform(post(PATH_DOGS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordDogDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de cachorros")
    void whenGETIsCalledThenReturnDogsPagedList() throws Exception {
        //given
		Page<QueryDogDTO> dogPage = new PageImpl<>(List.of(queryDogDTO));
    	
    	// when
    	when(dogService.findAll(any(Pageable.class))).thenReturn(dogPage);

        // then
        mockMvc.perform(get(PATH_DOGS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryDogDTO.getId().toString())))
        				,jsonPath("$.content[0].registrationDate", is(queryDogDTO.getRegistrationDate()))
        				,jsonPath("$.content[0].registrationDate", is(queryDogDTO.getRegistrationDate()))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyPeoplePagedList() throws Exception {
        //given
		Page<QueryDogDTO> dogPage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(dogService.findAll(any(Pageable.class))).thenReturn(dogPage);

        // then
        mockMvc.perform(get(PATH_DOGS)
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
    @DisplayName("3. Quando GET é chamado com filtro para cliente, deve retornar lista paginada de cachorros")
    void whenGETIsCalledWithFilterByClientThenReturnDogsPagedList() throws Exception {
        //given
		Page<QueryDogDTO> dogPage = new PageImpl<>(List.of(queryDogDTO));
    	
    	// when
    	when(dogService.findAllByClientId(eq(ID),any(Pageable.class))).thenReturn(dogPage);

        // then
        mockMvc.perform(get(PATH_DOGS+"/clients/"+ID)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryDogDTO.getId().toString())))
        				,jsonPath("$.content[0].registrationDate", is(queryDogDTO.getRegistrationDate()))
        				,jsonPath("$.content[0].registrationDate", is(queryDogDTO.getRegistrationDate()))
        		);
    }
    
    @Test
    @DisplayName("4. Quando GET é chamado com id de cachorro válido, deve retornar detalhes de cachorro")
    void whenGETIsCalledWithValidDogIdShouldReturnDogDetails() throws Exception {
    	// when
    	when(dogService.findById(ID)).thenReturn(new EntityDtoBox<Dog, QueryDogDTO>(queryDogDTO, DTO));

        // then
        mockMvc.perform(get(PATH_DOGS+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryDogDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryDogDTO.getRegistrationDate()))
        		);
    }
    
    @Test
    @DisplayName("5. Quando PUT é chamado com id de cachorro válido, deve atualizar cachorro e retornar detalhes do cachorro")
    void whenPUTIsCalledWithValidDogIdShouldUpdateDogAndReturnDogDetails() throws Exception {
    	
    	// when
    	when(dogService.update(ID, recordDogDTO)).thenReturn(queryDogDTO);

        // then
        mockMvc.perform(put(PATH_DOGS+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordDogDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryDogDTO.getId().toString())))
    				,jsonPath("$.registrationDate", is(queryDogDTO.getRegistrationDate()))
    		);
    }
    
    @Test
    @DisplayName("6. Quando DELETE é chamado com id de cachorro válido, deve excluir cachorro e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidDogIdShouldUpdateDogAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(dogService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_DOGS+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(dogService, times(1)).deleteById(ID);
    }

}
