package com.gft.meuamigau.dtos.auth;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_PASSWORD_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_PASSWORD_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_PASSWORD_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_USERNAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_USERNAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_USERNAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthenticationDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			,title = SCHEMA_AUTH_USERNAME_TITLE
			, description = SCHEMA_AUTH_USERNAME_DESCRIPTION
			, example = SCHEMA_AUTH_USERNAME_EXAMPLE
			, maxLength = 100
			, required = true)
	private String username;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_AUTH_PASSWORD_TITLE
			, description = SCHEMA_AUTH_PASSWORD_DESCRIPTION
			, example = SCHEMA_AUTH_PASSWORD_EXAMPLE
			, maxLength = 70
			, required = true)
	private String password;

}
