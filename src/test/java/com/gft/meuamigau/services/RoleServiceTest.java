package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.RoleName.ROLE_ADMIN;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PERFIL_INEXISTENTE;
import static com.gft.meuamigau.enums.constants.ErrorMessages.PERFIL_JA_CADASTRADO;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.RoleUtils.ID;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.enums.RoleName;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.RoleRepository;
import com.gft.meuamigau.utils.RoleUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> RoleService")
@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
	
	private Role role;
	
	@Mock
    private RoleRepository roleRepository;
	
	@InjectMocks
    private RoleService roleService;
	
	@BeforeEach
	private void setup() {
		role = RoleUtils.createFakeEntity();
	}
	
	private void assertRole(Role result) {
		assertAll(
        		"Check if role was returned"
        		, () -> assertNotNull(result)
        		, () -> assertEquals(Role.class, result.getClass())
        		, () -> assertEquals(ID, result.getId())
        		, () -> assertEquals(ROLE_ADMIN, result.getName())
        );
	}
	
	@Test
	@DisplayName("1. Quando procurar por nome, deve retornar Role")
    void whenFindByNameThenShouldReturnRole() {
        
		//when
        when(roleRepository.findByName(ROLE_ADMIN)).thenReturn(Optional.of(role));
        
        //then
        assertRole(roleService.findByName(ROLE_ADMIN));
    }
	
	
	@Test
	@DisplayName("1.2. Quando procurar por nome inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidNameThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(roleRepository.findByName(any(RoleName.class))).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> roleService.findByName(ROLE_ADMIN)
				, EntityNotFoundException.class
				, String.format(PERFIL_INEXISTENTE,ROLE_ADMIN));    
	}
	
	@Test
	@DisplayName("2. Quando criar perfil, deve retornar perfil salvo")
    void whenCreateRoleShouldReturnSavedRole() {
		//when
		when(roleRepository.findByName(any(RoleName.class))).thenReturn(Optional.empty());
		when(roleRepository.save(any(Role.class))).thenReturn(role);
		
		//then
		assertRole(roleService.create(ROLE_ADMIN));
	}
	
	@Test
	@DisplayName("2.2. Quando tentar criar perfil com nome já existente, deve lançar DataIntegrityViolation com mensagem correta")
    void whenTryCreateRoleWithExistingNameShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		when(roleRepository.findByName(any(RoleName.class))).thenReturn(Optional.of(role));
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> roleService.create(ROLE_ADMIN)
				, DataIntegrityViolationException.class
				, PERFIL_JA_CADASTRADO);   
    
	}

}
