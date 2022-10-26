package com.gft.meuamigau.dtos.client;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractClientDTO {

	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_CLIENT_ID_TITLE
			, description = SCHEMA_CLIENT_ID_DESCRIPTION
			, example = SCHEMA_CLIENT_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_CLIENT_NAME_TITLE
			, description = SCHEMA_CLIENT_NAME_DESCRIPTION
			, example = SCHEMA_CLIENT_NAME_EXAMPLE)
	private String name;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_CLIENT_REGISTRATION_DATE_TITLE
			, description = SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE)
	private String registrationDate;

}
