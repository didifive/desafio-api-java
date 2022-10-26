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
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PERSON_TYPE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_PERSON_PHONE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.ValidationMessages.CPF_CNPJ_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.DEVE_INFORMAR_EMAIL_VALIDO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.DEVE_INFORMAR_UM_CNPJ_VALIDO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.DEVE_INFORMAR_UM_CPF_VALIDO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.EMAIL_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAME_NAO_PODE_SER_VAZIO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_45_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.PERSON_TYPE_NAO_PODE_SER_VAZIO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.gft.meuamigau.dtos.address.RecordAddressDTO;
import com.gft.meuamigau.enums.PersonType;
import com.gft.meuamigau.utils.person.CnpjGroup;
import com.gft.meuamigau.utils.person.CpfGroup;
import com.gft.meuamigau.utils.person.PersonGroupSequenceProvider;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GroupSequenceProvider(PersonGroupSequenceProvider.class)
public class RecordPersonDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_NAME_TITLE
			, description = SCHEMA_PERSON_NAME_DESCRIPTION
			, example = SCHEMA_PERSON_NAME_EXAMPLE
			, required = true
			, maxLength = 255)
	@NotEmpty(message = NAME_NAO_PODE_SER_VAZIO)
	@Size(max = 255, message = NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String name;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_PERSON_TYPE_TITLE
			, description = SCHEMA_PERSON_PERSON_TYPE_DESCRIPTION
			, example = SCHEMA_PERSON_PERSON_TYPE_EXAMPLE
			, required = true)
	@NotNull(message = PERSON_TYPE_NAO_PODE_SER_VAZIO)
	@Enumerated(EnumType.STRING)
	private PersonType personType;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_CPF_CNPJ_TITLE
			, description = SCHEMA_PERSON_CPF_CNPJ_DESCRIPTION
			, example = SCHEMA_PERSON_CPF_CNPJ_EXAMPLE
			, required = true
			, maxLength = 14)
	@NotEmpty(message = CPF_CNPJ_NAO_PODE_SER_VAZIO)
	@CPF(groups = CpfGroup.class, message = DEVE_INFORMAR_UM_CPF_VALIDO)
	@CNPJ(groups = CnpjGroup.class, message = DEVE_INFORMAR_UM_CNPJ_VALIDO)
	private String cpfCnpj;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_BIRTH_DATE_TITLE
			, description = SCHEMA_PERSON_BIRTH_DATE_DESCRIPTION
			, example = SCHEMA_PERSON_BIRTH_DATE_EXAMPLE
			, required = false
			, maxLength = 10)
	@Size(max = 10, message = NAO_PODE_CONTER_MAIS_QUE_10_CARACTERES)
	private String birthDate;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_PHONE_TITLE
			, description = SCHEMA_PERSON_PHONE_DESCRIPTION
			, example = SCHEMA_PERSON_PHONE_EXAMPLE
			, required = false
			, maxLength = 45)
	@Size(max = 45, message = NAO_PODE_CONTER_MAIS_QUE_45_CARACTERES)
	private String phone;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_PERSON_E_MAIL_TITLE
			, description = SCHEMA_PERSON_E_MAIL_DESCRIPTION
			, example = SCHEMA_PERSON_E_MAIL_EXAMPLE
			, required = true
			, maxLength = 255)
	@NotEmpty(message = EMAIL_NAO_PODE_SER_VAZIO)
	@Email(message = DEVE_INFORMAR_EMAIL_VALIDO)
	private String email;

	@Builder.Default
	private List<RecordAddressDTO> addresses = new ArrayList<>();
	
}
