package com.gft.meuamigau.dtos.person;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_BIRTH_DATE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_BIRTH_DATE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_BIRTH_DATE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_CPF_CNPJ_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_CPF_CNPJ_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_CPF_CNPJ_TITLE;
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
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_USERNAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_USERNAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_USERNAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gft.meuamigau.dtos.address.QueryAddressDTO;
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
public class QueryPersonDTO {
	
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
			, title = SCHEMA_PERSON_CPF_CNPJ_TITLE
			, description = SCHEMA_PERSON_CPF_CNPJ_DESCRIPTION
			, example = SCHEMA_PERSON_CPF_CNPJ_EXAMPLE
			, maxLength = 14)
	private String cpfCnpj;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_E_MAIL_TITLE
			, description = SCHEMA_PERSON_E_MAIL_DESCRIPTION
			, example = SCHEMA_PERSON_E_MAIL_EXAMPLE
			, maxLength = 255)
	private String email;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_BIRTH_DATE_TITLE
			, description = SCHEMA_PERSON_BIRTH_DATE_DESCRIPTION
			, example = SCHEMA_PERSON_BIRTH_DATE_EXAMPLE
			, nullable = true)
	private String birthDate;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_PHONE_TITLE
			, description = SCHEMA_PERSON_PHONE_DESCRIPTION
			, example = SCHEMA_PERSON_PHONE_EXAMPLE
			, nullable = true
			, maxLength = 45)
	private String phone;
	
	@Builder.Default
	private List<QueryAddressDTO> addresses = new ArrayList<>();
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_USERNAME_TITLE
			, description = SCHEMA_PERSON_USERNAME_DESCRIPTION
			, example = SCHEMA_PERSON_USERNAME_EXAMPLE
			, nullable = true
			, maxLength = 45)
	private String username;
	
}
