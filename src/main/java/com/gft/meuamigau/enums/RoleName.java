package com.gft.meuamigau.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(implementation = RoleName.class)
@Getter
public enum RoleName {
	
	ROLE_ADMIN,
	ROLE_USER,
	ROLE_PERSON,
	ROLE_CLIENT,
	ROLE_VET

}
