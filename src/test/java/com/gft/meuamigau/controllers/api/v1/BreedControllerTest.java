package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_BREEDS;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.exceptions.CustomRestExceptionHandler;
import com.gft.meuamigau.services.BreedService;
import com.gft.meuamigau.utils.BreedUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> BreedController")
@ExtendWith(MockitoExtension.class)
class BreedControllerTest {
	
	private MockMvc mockMvc;
	private QueryBreedDTO queryBreedDTO;
	
	private static final Long ID = 1L;
	private static final String NAME_QUERY = "pin";
	private static final String IMAGE_QUERY = "H6h17XOYn";

	@Mock
	private BreedService breedService;
	
    @InjectMocks
    private BreedController breedController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(breedController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new CustomRestExceptionHandler())
                .build();
        queryBreedDTO = BreedUtils.createFakeQueryBreedDTO();
    }
    
    @Test
    @DisplayName("1. Quando GET é chamado, deve retornar lista paginada de raças")
    void whenGETIsCalledThenReturnBreedPagedList() throws Exception {
        //given
		Page<QueryBreedDTO> breedPage = new PageImpl<>(List.of(queryBreedDTO));
    	
    	// when
    	when(breedService.findAll(any(Pageable.class))).thenReturn(breedPage);

        // then
        mockMvc.perform(get(PATH_BREEDS)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryBreedDTO.getId().toString())))
        				,jsonPath("$.content[0].name", is(queryBreedDTO.getName()))
        				,jsonPath("$.content[0].lifeSpan", is(queryBreedDTO.getLifeSpan()))
        				,jsonPath("$.content[0].bredFor", is(queryBreedDTO.getBredFor()))
        				,jsonPath("$.content[0].breedGroup", is(queryBreedDTO.getBreedGroup()))
        				,jsonPath("$.content[0].referenceImageId", is(queryBreedDTO.getReferenceImageId()))
        				,jsonPath("$.content[0].temperament", is(queryBreedDTO.getTemperament()))
        				,jsonPath("$.content[0].weightImperial", is(queryBreedDTO.getWeightImperial()))
        				,jsonPath("$.content[0].weightMetric", is(queryBreedDTO.getWeightMetric()))
        				,jsonPath("$.content[0].heightImperial", is(queryBreedDTO.getHeightImperial()))
        				,jsonPath("$.content[0].heightMetric", is(queryBreedDTO.getHeightMetric()))
        		);
    }
    
    @Test
    @DisplayName("2. Quando GET é chamado por nome, deve retornar lista paginada de raças filtrada por nome")
    void whenGETIsCalledByNameThenReturnBreedPagedListFilteredByName() throws Exception {
        //given
		Page<QueryBreedDTO> breedPage = new PageImpl<>(List.of(queryBreedDTO));
    	
    	// when
    	when(breedService.findByName(eq(NAME_QUERY),any(Pageable.class))).thenReturn(breedPage);

        // then
        mockMvc.perform(get(PATH_BREEDS+"/name/"+NAME_QUERY)
            		.queryParam("page", "0")
            		.queryParam("size", "5")
        			.queryParam("sort", "id,desc")
        			)
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.content[0].id", is(Integer.parseInt(queryBreedDTO.getId().toString())))
        				,jsonPath("$.content[0].name", is(queryBreedDTO.getName()))
        				,jsonPath("$.content[0].lifeSpan", is(queryBreedDTO.getLifeSpan()))
        				,jsonPath("$.content[0].bredFor", is(queryBreedDTO.getBredFor()))
        				,jsonPath("$.content[0].breedGroup", is(queryBreedDTO.getBreedGroup()))
        				,jsonPath("$.content[0].referenceImageId", is(queryBreedDTO.getReferenceImageId()))
        				,jsonPath("$.content[0].temperament", is(queryBreedDTO.getTemperament()))
        				,jsonPath("$.content[0].weightImperial", is(queryBreedDTO.getWeightImperial()))
        				,jsonPath("$.content[0].weightMetric", is(queryBreedDTO.getWeightMetric()))
        				,jsonPath("$.content[0].heightImperial", is(queryBreedDTO.getHeightImperial()))
        				,jsonPath("$.content[0].heightMetric", is(queryBreedDTO.getHeightMetric()))
        		);
    }
    
    @Test
    @DisplayName("3. Quando GET é chamado com id de raça válido, deve retornar detalhes da raça")
    void whenGETIsCalledWithValidBreedIdShouldReturnBreedDetails() throws Exception {
    	// when
    	when(breedService.findById(ID)).thenReturn(queryBreedDTO);

        // then
        mockMvc.perform(get(PATH_BREEDS+"/"+ID))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(Integer.parseInt(queryBreedDTO.getId().toString())))
        				,jsonPath("$.name", is(queryBreedDTO.getName()))
        				,jsonPath("$.lifeSpan", is(queryBreedDTO.getLifeSpan()))
        				,jsonPath("$.bredFor", is(queryBreedDTO.getBredFor()))
        				,jsonPath("$.breedGroup", is(queryBreedDTO.getBreedGroup()))
        				,jsonPath("$.referenceImageId", is(queryBreedDTO.getReferenceImageId()))
        				,jsonPath("$.temperament", is(queryBreedDTO.getTemperament()))
        				,jsonPath("$.weightImperial", is(queryBreedDTO.getWeightImperial()))
        				,jsonPath("$.weightMetric", is(queryBreedDTO.getWeightMetric()))
        				,jsonPath("$.heightImperial", is(queryBreedDTO.getHeightImperial()))
        				,jsonPath("$.heightMetric", is(queryBreedDTO.getHeightMetric()))
        		);
    }
    
    @Test
    @DisplayName("4. Quando GET é chamado em imagem com id de imagem válido, deve retornar detalhes da imagem")
    void whenGETIsCalledInImageWithValidImageIdShouldReturnImageDetails() throws Exception {
    	//given
    	ImageBreedDTO imageBreedDTO = BreedUtils.createFakeImageBreedDTO();
    	
    	// when
    	when(breedService.findImageById(IMAGE_QUERY)).thenReturn(imageBreedDTO);

        // then
        mockMvc.perform(get(PATH_BREEDS+"/images/"+IMAGE_QUERY))
        		.andExpectAll(
        				status().isOk()
        				,jsonPath("$.id", is(imageBreedDTO.getId()))
        				,jsonPath("$.url", is(imageBreedDTO.getUrl()))
        				,jsonPath("$.width", is(imageBreedDTO.getWidth()))
        				,jsonPath("$.height", is(imageBreedDTO.getHeight()))
        		);
    }

}
