package com.gft.meuamigau.dtos.vet;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_TITLE;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbstractVetDTO {

	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_VET_ID_TITLE
			, description = SCHEMA_VET_ID_DESCRIPTION
			, example = SCHEMA_VET_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_NAME_TITLE
			, description = SCHEMA_VET_NAME_DESCRIPTION
			, example = SCHEMA_VET_NAME_EXAMPLE)
	private String name;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_REGISTRATION_DATE_TITLE
			, description = SCHEMA_VET_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_VET_REGISTRATION_DATE_EXAMPLE)
	private String registrationDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_SPECIALITY_TITLE
			, description = SCHEMA_VET_SPECIALITY_DESCRIPTION
			, example = SCHEMA_VET_SPECIALITY_EXAMPLE
			, maxLength = 255)
	private String speciality;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_CRMV_TITLE
			, description = SCHEMA_VET_CRMV_DESCRIPTION
			, example = SCHEMA_VET_CRMV_EXAMPLE
			, maxLength = 45)
	private String crmv;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_CRMV_UF_TITLE
			, description = SCHEMA_VET_CRMV_UF_DESCRIPTION
			, example = SCHEMA_VET_CRMV_UF_EXAMPLE
			, maxLength = 2)
	private String crmvUf;

}
