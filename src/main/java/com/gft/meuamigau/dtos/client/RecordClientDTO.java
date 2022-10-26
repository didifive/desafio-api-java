package com.gft.meuamigau.dtos.client;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_PERSON_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_PERSON_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_PERSON_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_CLIENT_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.ValidationMessages.ID_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordClientDTO {
	
	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_CLIENT_PERSON_ID_TITLE
			, description = SCHEMA_CLIENT_PERSON_ID_DESCRIPTION
			, example = SCHEMA_CLIENT_PERSON_ID_EXAMPLE
			, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long personId;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_CLIENT_REGISTRATION_DATE_TITLE
			, description = SCHEMA_CLIENT_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_CLIENT_REGISTRATION_DATE_EXAMPLE
			, required = false
			, maxLength = 10)
	@Size(max = 10, message = NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES)
	private String registrationDate;
	
}
