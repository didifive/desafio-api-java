package com.gft.meuamigau;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_BEARER_AUTH;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_DESCRIPTION;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = SECURITY_SCHEME_BEARER_AUTH
				, description = SECURITY_SCHEME_DESCRIPTION
				, scheme = "bearer"
				, bearerFormat = "JWT"
				, type = SecuritySchemeType.HTTP
				, in = SecuritySchemeIn.HEADER)
public class MeuAmigauApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuAmigauApiApplication.class, args);
	}

}
