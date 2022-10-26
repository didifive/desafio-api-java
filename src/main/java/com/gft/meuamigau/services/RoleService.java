package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.constants.ErrorMessages.PERFIL_INEXISTENTE;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PERFIL_JA_CADASTRADO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.enums.RoleName;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	public Role findByName(RoleName name) {
		return roleRepository.findByName(name).orElseThrow(
				() -> new EntityNotFoundException(
						String.format(PERFIL_INEXISTENTE, name.toString())));
	}
	
	public Role create(RoleName roleName) {
		if (roleRepository.findByName(roleName).isPresent())
			throw new DataIntegrityViolationException(PERFIL_JA_CADASTRADO);
		
		Role newRole = new Role(null, roleName, new ArrayList<>());
		
		return roleRepository.save(newRole);
	}

}
