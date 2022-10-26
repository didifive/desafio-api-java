package com.gft.meuamigau.dtos.user;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_ARRAY;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_NUMBER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ID_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ROLE_NAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ROLE_NAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_ROLE_NAME_TITLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_EXAMPLE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_USER_USERNAME_TITLE;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gft.meuamigau.dtos.person.AbstractPersonDTO;
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
public class QueryUserDTO {

	@Schema(type = SCHEMA_TYPE_NUMBER
			, title = SCHEMA_USER_ID_TITLE
			, description = SCHEMA_USER_ID_DESCRIPTION
			, example = SCHEMA_USER_ID_EXAMPLE)
	private Long id;
	
	@Schema(type = SCHEMA_TYPE_STRING
			, title = SCHEMA_USER_USERNAME_TITLE
			, description = SCHEMA_USER_USERNAME_DESCRIPTION
			, example = SCHEMA_USER_USERNAME_EXAMPLE
			, maxLength = 100)
	private String username;
	
	@Schema(type = SCHEMA_TYPE_ARRAY
			, title = SCHEMA_USER_ROLE_NAME_TITLE
			, description = SCHEMA_USER_ROLE_NAME_DESCRIPTION
			, example = SCHEMA_USER_ROLE_NAME_EXAMPLE)
	@Enumerated(EnumType.STRING)
	private List<RoleName> rolesNames;
	
	@JsonInclude(value = Include.NON_NULL)
	private AbstractPersonDTO person;
}
