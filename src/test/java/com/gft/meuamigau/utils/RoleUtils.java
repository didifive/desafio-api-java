package com.gft.meuamigau.utils;

import static com.gft.meuamigau.enums.RoleName.ROLE_ADMIN;

import java.util.ArrayList;

import com.gft.meuamigau.entities.Role;

public class RoleUtils {
	
	public static final Long ID = 1L;
	
 public static Role createFakeEntity() {
		return new Role(
					ID
					, ROLE_ADMIN
					, new ArrayList<>()
				);
	}
	
}
