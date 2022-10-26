package com.gft.meuamigau.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Controllers -> MainController")
@ExtendWith(MockitoExtension.class)@ExtendWith(MockitoExtension.class)
class MainControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private MainController mainController;
	
	@BeforeEach
	private void setup() {
	      mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}
		
	@Test
	@DisplayName("1. quando GET é chamado, deve redirecionar para página do swagger")
	void whenGETIsCalledShouldRedirectToSwaggerPage() throws Exception {
		//then
	  	mockMvc.perform(get("/"))
	  		.andExpectAll(
	  			status().isFound(),
	  			view().name("redirect:/swagger-ui.html")
	  	);
	  	
	}
}
