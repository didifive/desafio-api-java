package com.gft.meuamigau.dtos.user;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_NEW_PASSWORD_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_NEW_PASSWORD_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_NEW_PASSWORD_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_OLD_PASSWORD_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_OLD_PASSWORD_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_OLD_PASSWORD_TITLE;
import static com.gft.meuamigau.enums.constants.ValidationMessages.PASSWORD_DEVE_SER_INFORMADO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES;

import javax.validation.constraints.NotEmpty;
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
public class PasswordUserDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_USER_OLD_PASSWORD_TITLE
			, description = SCHEMA_USER_OLD_PASSWORD_DESCRIPTION
			, example = SCHEMA_USER_OLD_PASSWORD_EXAMPLE
			, maxLength = 100)
	@NotEmpty(message = PASSWORD_DEVE_SER_INFORMADO)
	@Size(min = 6, max = 25, message = PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES)
	private String oldPassword;

	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_USER_NEW_PASSWORD_TITLE
			, description = SCHEMA_USER_NEW_PASSWORD_DESCRIPTION
			, example = SCHEMA_USER_NEW_PASSWORD_EXAMPLE
			, maxLength = 100)
	@NotEmpty(message = PASSWORD_DEVE_SER_INFORMADO)
	@Size(min = 6, max = 25, message = PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES)
	private String newPassword;

}
