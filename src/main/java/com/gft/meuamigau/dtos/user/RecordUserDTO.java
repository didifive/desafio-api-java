package com.gft.meuamigau.dtos.user;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_TITLE;
import static com.gft.meuamigau.enums.constants.ValidationMessages.PASSWORD_DEVE_SER_INFORMADO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES;
import static com.gft.meuamigau.enums.constants.ValidationMessages.USERNAME_DEVE_SER_INFORMADO;
import static com.gft.meuamigau.enums.constants.ValidationMessages.USERNAME_INFORMADO_DEVE_TER_MINIMO_DE_2_E_MAXIMO_DE_100_CARACTERES;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.gft.meuamigau.enums.RoleName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordUserDTO {
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_USER_USERNAME_TITLE
			, description = SCHEMA_USER_USERNAME_DESCRIPTION
			, example = SCHEMA_USER_USERNAME_EXAMPLE
			, maxLength = 100)
	@NotEmpty(message = USERNAME_DEVE_SER_INFORMADO)
	@Size(min = 2, max = 100, message = USERNAME_INFORMADO_DEVE_TER_MINIMO_DE_2_E_MAXIMO_DE_100_CARACTERES)
	private String username;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_USER_USERNAME_TITLE
			, description = SCHEMA_USER_USERNAME_DESCRIPTION
			, example = SCHEMA_USER_USERNAME_EXAMPLE
			, minLength = 6
			, maxLength = 25)
	@NotEmpty(message = PASSWORD_DEVE_SER_INFORMADO)
	@Size(min = 6, max = 25, message = PASSWORD_INFORMADO_DEVE_TER_MINIMO_DE_6_E_MAXIMO_DE_25_CARACTERES)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private List<RoleName> rolesNames;

}
