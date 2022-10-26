package com.gft.meuamigau.dtos.address;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ADDRESS_TYPE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_COMPLEMENT_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_NUMBER_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_PUBLIC_PLACE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_ADDRESS_ZIP_CODE_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gft.meuamigau.enums.AddressType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAddressDTO {

	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_ADDRESS_ID_TITLE
			, description = SCHEMA_ADDRESS_ID_DESCRIPTION
			, example = SCHEMA_ADDRESS_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_PUBLIC_PLACE_TITLE
			, description = SCHEMA_ADDRESS_PUBLIC_PLACE_DESCRIPTION
			, example = SCHEMA_ADDRESS_PUBLIC_PLACE_EXAMPLE
			, maxLength = 255)
	private String publicPlace;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_NUMBER_TITLE
			, description = SCHEMA_ADDRESS_NUMBER_DESCRIPTION
			, example = SCHEMA_ADDRESS_NUMBER_EXAMPLE
			, maxLength = 15)
	private String number;
	
	@JsonInclude(value = Include.NON_NULL)
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_COMPLEMENT_TITLE
			, description = SCHEMA_ADDRESS_COMPLEMENT_DESCRIPTION
			, example = SCHEMA_ADDRESS_COMPLEMENT_EXAMPLE
			, maxLength = 255
			, nullable = true)
	private String complement;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_ZIP_CODE_TITLE
			, description = SCHEMA_ADDRESS_ZIP_CODE_DESCRIPTION
			, example = SCHEMA_ADDRESS_ZIP_CODE_EXAMPLE
			, minLength = 8
			, maxLength = 8)
	private String zipCode;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_ADDRESS_ADDRESS_TYPE_TITLE
			, description = SCHEMA_ADDRESS_ADDRESS_TYPE_DESCRIPTION
			, example = SCHEMA_ADDRESS_ADDRESS_TYPE_EXAMPLE)
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

}
