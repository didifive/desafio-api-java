package com.gft.meuamigau.dtos.dog;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
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
public class AbstractDogDTO {
	
	@Schema(type = SCHEMA_TYPE_INTEGER
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
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_BREED_NAME_TITLE
			, description = SCHEMA_DOG_BREED_NAME_DESCRIPTION
			, example = SCHEMA_DOG_BREED_NAME_EXAMPLE
			, maxLength = 255)
	private String breedName;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_COLOR_TITLE
			, description = SCHEMA_DOG_COLOR_DESCRIPTION
			, example = SCHEMA_DOG_COLOR_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String color;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_BIRTH_DATE_TITLE
			, description = SCHEMA_DOG_BIRTH_DATE_DESCRIPTION
			, example = SCHEMA_DOG_BIRTH_DATE_EXAMPLE
			, nullable = true)
	private String birthDate;
	
}
