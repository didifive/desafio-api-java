package com.gft.meuamigau.dtos.auth;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_TOKEN_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_TOKEN_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_AUTH_TOKEN_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_AUTH_TOKEN_TITLE
			, description = SCHEMA_AUTH_TOKEN_DESCRIPTION
			, example = SCHEMA_AUTH_TOKEN_EXAMPLE)
	private String token;

}
