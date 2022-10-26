package com.gft.meuamigau.dtos.dog;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BIRTH_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_BREED_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_CLIENT_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_CLIENT_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_CLIENT_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_COLOR_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_DOG_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.ValidationMessages.ID_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAME_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDogDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_NAME_TITLE
			, description = SCHEMA_DOG_NAME_DESCRIPTION
			, example = SCHEMA_DOG_NAME_EXAMPLE
			, required = true
			, maxLength = 255)
	@NotEmpty(message = NAME_NAO_PODE_SER_VAZIO)
	@Size(max = 255, message = NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String name;
	
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_DOG_BREED_ID_TITLE
        	, description = SCHEMA_DOG_BREED_ID_DESCRIPTION
        	, example = SCHEMA_DOG_BREED_ID_EXAMPLE
        	, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long breedId;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_COLOR_TITLE
			, description = SCHEMA_DOG_COLOR_DESCRIPTION
			, example = SCHEMA_DOG_COLOR_EXAMPLE
			, maxLength = 255)
	@Size(max = 255, message = NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String color;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_BIRTH_DATE_TITLE
			, description = SCHEMA_DOG_BIRTH_DATE_DESCRIPTION
			, example = SCHEMA_DOG_BIRTH_DATE_EXAMPLE
			, required = false
			, maxLength = 10)
	@Size(max = 10, message = NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES)
	private String birthDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_DOG_REGISTRATION_DATE_TITLE
			, description = SCHEMA_DOG_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_DOG_REGISTRATION_DATE_EXAMPLE
			, required = false
			, maxLength = 10)
	@Size(max = 10, message = NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES)
	private String registrationDate;
	
	@Schema(type = SCHEMA_TYPE_NUMBER
        	, title = SCHEMA_DOG_CLIENT_ID_TITLE
        	, description = SCHEMA_DOG_CLIENT_ID_DESCRIPTION
        	, example = SCHEMA_DOG_CLIENT_ID_EXAMPLE
        	, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long clientId;
	
}
