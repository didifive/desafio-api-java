package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES;
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

import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.entities.Attendance;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.AttendanceService;
import com.gft.meuamigau.utils.AttendanceUtils;
import com.gft.meuamigau.utils.EntityDtoBox;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> AttendanceController")
@ExtendWith(MockitoExtension.class)
class AttendanceControllerTest {
	
	private static final Long ID = 1L;
	
	private MockMvc mockMvc;
	
	private RecordAttendanceDTO recordAttendanceDTO;
	private QueryAttendanceDTO queryAttendanceDTO;
	private Page<QueryAttendanceDTO> attendancePage;

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        recordAttendanceDTO = AttendanceUtils.createFakeRecordAttendanceDTO();
        queryAttendanceDTO = AttendanceUtils.createFakeQueryAttendanceDTO();
        attendancePage = new PageImpl<>(List.of(queryAttendanceDTO));
    }

    @Test
    @DisplayName("1. Quando POST é chamado, deve salvar atendimento e retornar dados básicos e header Location correto")
    void whenPOSTIsCalledShouldCreateAttendanceAndReturnBasicDataAndCorrectHeaderLocation() throws Exception {
        // when
        when(attendanceService.create(recordAttendanceDTO)).thenReturn(queryAttendanceDTO);

        // then
        mockMvc.perform(post(PATH_ATTENDANCES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordAttendanceDTO)))
        .andDo(print())
        		.andExpectAll(
        				status().isCreated()
        				,jsonPath("$.id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        				,header().string("Location", URI_BASE+PATH_ATTENDANCES+"/"+queryAttendanceDTO.getId())
        				);
    }
    
    @Test
    @DisplayName("1.2. Quando POST é chamado com dado inválido, deve retornar Bad Request")
    void whenPOSTIsCalledWithInvalidDataShouldReturnBadRequest() throws Exception {
        //given
    	recordAttendanceDTO.setDogId(null);

        // then
        mockMvc.perform(post(PATH_ATTENDANCES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(recordAttendanceDTO)))
        		.andExpect(status().isBadRequest());
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado, deve retornar lista paginada de atendimentos")
    void whenGETIsCalledThenReturnAttendancePagedList() throws Exception {
    	// when
    	when(attendanceService.findAll(any(Pageable.class))).thenReturn(attendancePage);

        // then
        mockMvc.perform(get(PATH_ATTENDANCES)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.content[0].dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.content[0].client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.content[0].vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("2.2. Quando GET é chamado, deve retornar lista paginada vazia")
    void whenGETIsCalledThenReturnEmptyAttendancePagedList() throws Exception {
        //given
    	attendancePage = new PageImpl<>(Collections.emptyList());
    	
    	// when
    	when(attendanceService.findAll(any(Pageable.class))).thenReturn(attendancePage);

        // then
        mockMvc.perform(get(PATH_ATTENDANCES)
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
    @DisplayName("3. Quando GET é chamado com filtro para cliente, deve retornar lista paginada de atendimentos")
    void whenGETIsCalledWithFilterByClientThenReturnAttendancePagedList() throws Exception {
    	// when
    	when(attendanceService.findAllByClientId(eq(ID),any(Pageable.class))).thenReturn(attendancePage);

        // then
        mockMvc.perform(get(PATH_ATTENDANCES+"/clients/"+ID)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.content[0].dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.content[0].client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.content[0].vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("4. Quando GET é chamado com filtro para veterinário, deve retornar lista paginada de atendimentos")
    void whenGETIsCalledWithFilterByVetThenReturnAttendancePagedList() throws Exception {
    	// when
    	when(attendanceService.findAllByVetId(eq(ID),any(Pageable.class))).thenReturn(attendancePage);

        // then
        mockMvc.perform(get(PATH_ATTENDANCES+"/vets/"+ID)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.content[0].dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.content[0].client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.content[0].vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("5. Quando GET é chamado com filtro para cachorro, deve retornar lista paginada de atendimentos")
    void whenGETIsCalledWithFilterByDogThenReturnAttendancePagedList() throws Exception {
    	// when
    	when(attendanceService.findAllByDogId(eq(ID),any(Pageable.class))).thenReturn(attendancePage);

        // then
        mockMvc.perform(get(PATH_ATTENDANCES+"/dogs/"+ID)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.content[0].dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.content[0].client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.content[0].vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("6. Quando GET é chamado com id de atendimento válido, deve retornar detalhes de atendimento")
    void whenGETIsCalledWithValidAttendanceIdShouldReturnAttendanceDetails() throws Exception {
    	// when
    	when(attendanceService.findById(ID)).thenReturn(new EntityDtoBox<Attendance, QueryAttendanceDTO>(queryAttendanceDTO, DTO));

        // then
        mockMvc.perform(get(PATH_ATTENDANCES+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
        				,jsonPath("$.dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
        				,jsonPath("$.client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
        				,jsonPath("$.vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
        		);
    }
    
    @Test
    @DisplayName("7. Quando PUT é chamado com id de atendimento válido, deve atualizar atendimento e retornar detalhes do atendimento")
    void whenPUTIsCalledWithValidAttendanceIdShouldUpdateAttendanceAndReturnAttendanceDetails() throws Exception {
    	// when
    	when(attendanceService.update(ID, recordAttendanceDTO)).thenReturn(queryAttendanceDTO);

        // then
        mockMvc.perform(put(PATH_ATTENDANCES+"/"+ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recordAttendanceDTO)))
    		.andExpectAll(
    				status().isOk()
    				,jsonPath("$.id", is(Integer.parseInt(queryAttendanceDTO.getId().toString())))
    				,jsonPath("$.dog.id", is(Integer.parseInt(queryAttendanceDTO.getDog().getId().toString())))
    				,jsonPath("$.client.id", is(Integer.parseInt(queryAttendanceDTO.getClient().getId().toString())))
    				,jsonPath("$.vet.id", is(Integer.parseInt(queryAttendanceDTO.getVet().getId().toString())))
    		);
    }
    
    @Test
    @DisplayName("6. Quando DELETE é chamado com id de atendimento válido, deve excluir atendimento e retornar sem conteúdo")
    void whenDELETEIsCalledWithValidAttendanceIdShouldDeleteAttendanceAndReturnNoContent() throws Exception {
    	// when
    	doNothing().when(attendanceService).deleteById(ID);

        // then
        mockMvc.perform(delete(PATH_ATTENDANCES+"/"+ID))
    		.andExpect(status().isNoContent());
        verify(attendanceService, times(1)).deleteById(ID);
    }

}
