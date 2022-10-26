package com.gft.meuamigau.config.populate;

import static com.gft.meuamigau.enums.RoleName.ROLE_ADMIN;
import static com.gft.meuamigau.enums.RoleName.ROLE_CLIENT;
import static com.gft.meuamigau.enums.RoleName.ROLE_PERSON;
import static com.gft.meuamigau.enums.RoleName.ROLE_USER;
import static com.gft.meuamigau.enums.RoleName.ROLE_VET;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.services.RoleService;
import com.gft.meuamigau.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserPopulate {	
	private static final String ADMIN_GFT_COM = "admin@email.com";
	private static final String USUARIO_GFT_COM = "usuario@email.com";
	private static final String DEFAULT_PASSWORD = "pass@1234";
	
	private final UserService userService;

	private final RoleService roleService;

	public void createRolesAndUsers() {
		
		try {
			roleService.findByName(ROLE_ADMIN);
		} catch (EntityNotFoundException e) {
			roleService.create(ROLE_ADMIN);
			roleService.create(ROLE_USER);
			roleService.create(ROLE_PERSON);
			roleService.create(ROLE_VET);
			roleService.create(ROLE_CLIENT);
		}
		
		try {
			userService.loadUserByUsername(ADMIN_GFT_COM);
		} catch (UsernameNotFoundException e) {
			userService.create(new RecordUserDTO(
					ADMIN_GFT_COM
					, DEFAULT_PASSWORD
					, List.of(ROLE_ADMIN, ROLE_USER)));
			userService.create(new RecordUserDTO(
					USUARIO_GFT_COM
					, DEFAULT_PASSWORD
					, List.of(ROLE_USER)));
		}
		
    }
}