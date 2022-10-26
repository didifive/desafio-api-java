package com.gft.meuamigau.dtos.dog;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.dtos.client.AbstractClientDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryDogDTO {
	
	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_DOG_ID_TITLE
			, description = SCHEMA_DOG_ID_DESCRIPTION
			, example = SCHEMA_DOG_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_NAME_TITLE
			, description = SCHEMA_DOG_NAME_DESCRIPTION
			, example = SCHEMA_DOG_NAME_EXAMPLE
			, maxLength = 255)
	private String name;
	
	@Schema
	private QueryBreedDTO breed;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_COLOR_TITLE
			, description = SCHEMA_DOG_COLOR_DESCRIPTION
			, example = SCHEMA_DOG_COLOR_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String color;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_BIRTH_DATE_TITLE
			, description = SCHEMA_DOG_BIRTH_DATE_DESCRIPTION
			, example = SCHEMA_DOG_BIRTH_DATE_EXAMPLE
			, nullable = true)
	private String birthDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_REGISTRATION_DATE_TITLE
			, description = SCHEMA_DOG_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_DOG_REGISTRATION_DATE_EXAMPLE)
	private String registrationDate;
	
	private AbstractClientDTO client;
	
}
