package com.gft.meuamigau.dtos.address;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.gft.meuamigau.enums.AddressType;
import com.gft.meuamigau.enums.constants.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordAddressDTO {

	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_PUBLIC_PLACE_TITLE
			, description = SCHEMA_ADDRESS_PUBLIC_PLACE_DESCRIPTION
			, example = SCHEMA_ADDRESS_PUBLIC_PLACE_EXAMPLE
			, required = true
			, maxLength = 255)
	@NotEmpty(message = ValidationMessages.PUBLIC_PLACE_NAO_PODE_SER_VAZIO)
	@Size(max = 255, message = ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String publicPlace;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_NUMBER_TITLE
			, description = SCHEMA_ADDRESS_NUMBER_DESCRIPTION
			, example = SCHEMA_ADDRESS_NUMBER_EXAMPLE
			, required = true
			, maxLength = 15)
	@NotEmpty(message = ValidationMessages.NUMBER_NAO_PODE_SER_VAZIO)
	@Size(max = 15, message = ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_15_CARACTERES)
	private String number;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_COMPLEMENT_TITLE
			, description = SCHEMA_ADDRESS_COMPLEMENT_DESCRIPTION
			, example = SCHEMA_ADDRESS_COMPLEMENT_EXAMPLE
			, required = false
			, maxLength = 255)
	@Size(max = 255, message = ValidationMessages.NAO_PODE_CONTER_MAIS_QUE_255_CARACTERES)
	private String complement;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_ZIP_CODE_TITLE
			, description = SCHEMA_ADDRESS_ZIP_CODE_DESCRIPTION
			, example = SCHEMA_ADDRESS_ZIP_CODE_EXAMPLE
			, required = true
			, minLength = 8
			, maxLength = 8)
	@NotEmpty(message = ValidationMessages.ZIP_CODE_NAO_PODE_SER_VAZIO)
	@Size(min = 8, max = 8, message = ValidationMessages.DEVE_CONTER_8_CARACTERES)
	private String zipCode;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_ADDRESS_TYPE_TITLE
			, description = SCHEMA_ADDRESS_ADDRESS_TYPE_DESCRIPTION
			, example = SCHEMA_ADDRESS_ADDRESS_TYPE_EXAMPLE
			, required = true)
	@NotEmpty(message = ValidationMessages.ADDRESS_TYPE_NAO_PODE_SER_VAZIO)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
}
