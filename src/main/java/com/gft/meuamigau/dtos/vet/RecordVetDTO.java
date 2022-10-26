package com.gft.meuamigau.dtos.vet;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_CRMV_UF_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_PERSON_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_PERSON_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_PERSON_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_REGISTRATION_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_VET_SPECIALITY_TITLE;
import static com.gft.meuamigau.enums.constants.ValidationMessages.CRMV_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.CRMV_UF_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.ID_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.SPECIALITY_NAO_PODE_SER_VAZIO;

import javax.validation.constraints.NotEmpty;
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
public class RecordVetDTO {
	
	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_VET_PERSON_ID_TITLE
			, description = SCHEMA_VET_PERSON_ID_DESCRIPTION
			, example = SCHEMA_VET_PERSON_ID_EXAMPLE
			, required = true)
	@NotNull(message = ID_NAO_PODE_SER_VAZIO)
	private Long personId;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_REGISTRATION_DATE_TITLE
			, description = SCHEMA_VET_REGISTRATION_DATE_DESCRIPTION
			, example = SCHEMA_VET_REGISTRATION_DATE_EXAMPLE
			, maxLength = 10
			, required = false)
	@Size(max = 10, message = NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES)
	private String registrationDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_SPECIALITY_TITLE
			, description = SCHEMA_VET_SPECIALITY_DESCRIPTION
			, example = SCHEMA_VET_SPECIALITY_EXAMPLE
			, maxLength = 255
			, required = true)
	@NotEmpty(message = SPECIALITY_NAO_PODE_SER_VAZIO)
	private String speciality;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_CRMV_TITLE
			, description = SCHEMA_VET_CRMV_DESCRIPTION
			, example = SCHEMA_VET_CRMV_EXAMPLE
			, maxLength = 45
			, required = true)
	@NotEmpty(message = CRMV_NAO_PODE_SER_VAZIO)
	private String crmv;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_VET_CRMV_UF_TITLE
			, description = SCHEMA_VET_CRMV_UF_DESCRIPTION
			, example = SCHEMA_VET_CRMV_UF_EXAMPLE
			, maxLength = 2
			, required = true)
	@NotEmpty(message = CRMV_UF_NAO_PODE_SER_VAZIO)
	private String crmvUf;
	
}
