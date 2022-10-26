package com.gft.meuamigau.dtos.person;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_E_MAIL_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_E_MAIL_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_E_MAIL_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.gft.meuamigau.enums.PersonType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractPersonDTO {
	
	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_PERSON_ID_TITLE
			, description = SCHEMA_PERSON_ID_DESCRIPTION
			, example = SCHEMA_PERSON_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_NAME_TITLE
			, description = SCHEMA_PERSON_NAME_DESCRIPTION
			, example = SCHEMA_PERSON_NAME_EXAMPLE
			, maxLength = 255)
	private String name;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_PERSON_TYPE_TITLE
			, description = SCHEMA_PERSON_PERSON_TYPE_DESCRIPTION
			, example = SCHEMA_PERSON_PERSON_TYPE_EXAMPLE)
	@Enumerated(EnumType.STRING)
	private PersonType personType;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_E_MAIL_TITLE
			, description = SCHEMA_PERSON_E_MAIL_DESCRIPTION
			, example = SCHEMA_PERSON_E_MAIL_EXAMPLE
			, maxLength = 255)
	private String email;
	
}
