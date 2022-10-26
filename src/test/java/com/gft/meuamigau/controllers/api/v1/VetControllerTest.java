package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_VETS;
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

import com.gft.meuamigau.dtos.vet.QueryVetDTO;
import com.gft.meuamigau.dtos.vet.RecordVetDTO;
import com.gft.meuamigau.entities.Vet;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.VetService;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.VetUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> VetController")
@ExtendWith(MockitoExtension.class)
class VetControllerTest {
	
	private static final Long ID = 1L;
	private static final String NEW_DATE = "30/05/2022";
	
	private MockMvc mockMvc;
	private RecordVetDTO recordVetDTO;
	private QueryVetDTO queryVetDTO;

    @Mock
    private VetService vetService;

    @InjectMocks
    private VetController vetController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vetController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordVetDTO = VetUtils.createFakeRecordVetDTO();
        queryVetDTO = VetUtils.createFakeQueryVetDTO();
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar veterinário e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreateVetAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(vetService.create(recordVetDTO)).thenReturn(queryVetDTO);

        // then
        mockMvc.perform(post(PATH_VETS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordVetDTO)))
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryVetDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryVetDTO.getRegistrationDate()))
        				,jsonPath("$.speciality", is(queryVetDTO.getSpeciality()))
        				,jsonPath("$.crmv", is(queryVetDTO.getCrmv()))
        				,jsonPath("$.crmvUf", is(queryVetDTO.getCrmvUf()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryVetDTO.getPerson().getId().toString())))
        				,header().string("Location", URI_BASE+PATH_VETS+"/"+queryVetDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordVetDTO.setPersonId(null);

        // then
        mockMvc.perform(post(PATH_VETS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordVetDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de vetes")
    void whenGETIsCalledThenReturnVetsPagedList() throws Exception {
        //given
		Page<QueryVetDTO> vetPage = new PageImpl<>(List.of(queryVetDTO));
    	
    	// when
    	when(vetService.findAll(any(Pageable.class))).thenReturn(vetPage);

        // then
        mockMvc.perform(get(PATH_VETS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryVetDTO.getId().toString())))
        				,jsonPath("$.content[0].registrationDate", is(queryVetDTO.getRegistrationDate()))
        				,jsonPath("$.content[0].speciality", is(queryVetDTO.getSpeciality()))
        				,jsonPath("$.content[0].crmv", is(queryVetDTO.getCrmv()))
        				,jsonPath("$.content[0].crmvUf", is(queryVetDTO.getCrmvUf()))
        				,jsonPath("$.content[0].person.id", is(Integer.parseInt(queryVetDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyPeoplePagedList() throws Exception {
        //given
		Page<QueryVetDTO> vetPage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(vetService.findAll(any(Pageable.class))).thenReturn(vetPage);

        // then
        mockMvc.perform(get(PATH_VETS)
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
    @DisplayName("3. Quando GET é chamado com id de veterinário válido, deve retornar detalhes de veterinário")
    void whenGETIsCalledWithValidVetIdShouldReturnVetDetails() throws Exception {
    	// when
    	when(vetService.findById(ID)).thenReturn(new EntityDtoBox<Vet, QueryVetDTO>(queryVetDTO, DTO));

        // then
        mockMvc.perform(get(PATH_VETS+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryVetDTO.getId().toString())))
        				,jsonPath("$.registrationDate", is(queryVetDTO.getRegistrationDate()))
        				,jsonPath("$.speciality", is(queryVetDTO.getSpeciality()))
        				,jsonPath("$.crmv", is(queryVetDTO.getCrmv()))
        				,jsonPath("$.crmvUf", is(queryVetDTO.getCrmvUf()))
        				,jsonPath("$.person.id", is(Integer.parseInt(queryVetDTO.getPerson().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("4. Quando PUT é chamado com id de veterinário válido, deve atualizar veterinário e retornar detalhes do veterinário")
    void whenPUTIsCalledWithValidVetIdShouldUpdateVetAndReturnVetDetails() throws Exception {
    	//given
    	recordVetDTO.setRegistrationDate(NEW_DATE);
    	queryVetDTO.setRegistrationDate(NEW_DATE);
    	
    	// when
    	when(vetService.update(ID, recordVetDTO)).thenReturn(queryVetDTO);

        // then
        mockMvc.perform(put(PATH_VETS+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordVetDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryVetDTO.getId().toString())))
    				,jsonPath("$.registrationDate", is(queryVetDTO.getRegistrationDate()))
    				,jsonPath("$.speciality", is(queryVetDTO.getSpeciality()))
    				,jsonPath("$.crmv", is(queryVetDTO.getCrmv()))
    				,jsonPath("$.crmvUf", is(queryVetDTO.getCrmvUf()))
    				,jsonPath("$.person.id", is(Integer.parseInt(queryVetDTO.getPerson().getId().toString())))
    		);
    }
    
    @Test
    @DisplayName("5. Quando DELETE é chamado com id de veterinário válido, deve excluir veterinário e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidVetIdShouldUpdateVetAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(vetService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_VETS+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(vetService, times(1)).deleteById(ID);
    }

}
