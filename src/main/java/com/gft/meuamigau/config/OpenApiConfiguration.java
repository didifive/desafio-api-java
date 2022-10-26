package com.gft.meuamigau.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
	
    private static final String API_TITLE = "Meu AmigAU! API";
    private static final String API_DESCRIPTION = "Programa Starter GFT - Semana de Desafio API 18/07 ~ 29/07/2022";
    private static final String API_VERSION = "1.0.0";
    private static final String API_LICENSE = "Apache 2.0";
    private static final String API_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
    private static final String CONTACT_NAME = "Luis Carlos Zancanela";
    private static final String CONTACT_GITLAB = "https://git.gft.com/lsza";
    private static final String CONTACT_EMAIL = "luis.zancanela@gft.com";
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title(API_TITLE)
						.version(API_VERSION)
						.description(API_DESCRIPTION)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().
								name(API_LICENSE).
								url(API_LICENSE_URL))
						.contact(new Contact()
								.email(CONTACT_EMAIL)
								.name(CONTACT_NAME)
								.url(CONTACT_GITLAB)));
	}

}
