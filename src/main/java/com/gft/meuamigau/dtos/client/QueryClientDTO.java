package com.gft.meuamigau.dtos.client;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_USERNAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_USERNAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_USERNAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import java.util.List;

import com.gft.meuamigau.dtos.dog.AbstractDogDTO;
import com.gft.meuamigau.dtos.person.AbstractPersonDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryClientDTO {

	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_CLIENT_ID_TITLE
			, description = SCHEMA_CLIENT_ID_DESCRIPTION
			, example = SCHEMA_CLIENT_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_CLIENT_REGISTRATION_DATE_TITLE
			, description = SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE)
	private String registrationDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_CLIENT_USERNAME_TITLE
			, description = SCHEMA_CLIENT_USERNAME_DESCRIPTION
			, example = SCHEMA_CLIENT_USERNAME_EXAMPLE)
	private String username;
	
	private AbstractPersonDTO person;
	
	private List<AbstractDogDTO> dogs;

}
