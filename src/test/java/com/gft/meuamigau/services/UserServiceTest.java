package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.RoleName.ROLE_ADMIN;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USERNAME_JA_CADASTRADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_ATENDIMENTO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_PESSOA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_OU_SENHA_INVALIDOS;
import static com.gft.meuamigau.utils.Assertions.assertThrowsExceptionWithCorrectMessage;
import static com.gft.meuamigau.utils.UserUtils.ATTENDANCES;
import static com.gft.meuamigau.utils.UserUtils.ID;
import static com.gft.meuamigau.utils.UserUtils.PASSWORD;
import static com.gft.meuamigau.utils.UserUtils.ROLES;
import static com.gft.meuamigau.utils.UserUtils.ROLES_NAMES;
import static com.gft.meuamigau.utils.UserUtils.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gft.meuamigau.dtos.user.PasswordUserDTO;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.enums.RoleName;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.UserRepository;
import com.gft.meuamigau.utils.EntityDtoBox;
import com.gft.meuamigau.utils.RoleUtils;
import com.gft.meuamigau.utils.UserUtils;

@TestMethodOrder(MethodOrderer.DisplayName.class)
@DisplayName("Test Services -> UserService")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	private static final String NEW_PASSWORD = "789456";
	private static final String NEW_PASSWORD_ENCODED = new BCryptPasswordEncoder().encode(NEW_PASSWORD);
	private static final String OLD_PASSWORD = "123456";
	private static final String OLD_PASSWORD_ENCODED = new BCryptPasswordEncoder().encode(OLD_PASSWORD);
	private User user;
	private Role role;
	private RecordUserDTO givenUserDTO;
	
	private static final Long INVALID_ID = 2L;
	private static final String INVALID_USERNAME = "invalid@gft.com";
	private static final String NEW_USERNAME = "newusername@gft.com";
	
	@Mock
	private RoleService roleService;
	
	@Mock
    private UserRepository userRepository;
	
	@InjectMocks @Spy
    private UserService userService;
	
	@BeforeEach
	private void setup() {
		user = UserUtils.createFakeEntity();
		role = RoleUtils.createFakeEntity();
		givenUserDTO = UserUtils.createFakeRecordUserDTO();
	}
	
	@Test
	@DisplayName("1. Quando procurar por id, deve retornar User")
    void whenFindByIdThenShouldReturnUser() {
		//when
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        
        //then
        User foundedUser = userService.findById(ID,ENTITY).getEntity();
        assertAll(
        		"Check if user was returned"
        		, () -> assertNotNull(foundedUser)
        		, () -> assertEquals(User.class, 		foundedUser.getClass())
        		, () -> assertEquals(ID, 				foundedUser.getId())
        		, () -> assertEquals(USERNAME, 			foundedUser.getUsername())
        		, () -> assertEquals(PASSWORD, 			foundedUser.getPassword())
        		, () -> assertEquals(Set.of(role), 	foundedUser.getRoles())
        );
    }
	
	
	@Test
	@DisplayName("1.2. Quando procurar por id inválido, deve lançar EntityNotFoundException com mensagem correta")
    void whenFindByInvalidNameThenShouldThrowsEntityNotFoundExceptionWithCorrectMessage() {
		//when
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> userService.findById(INVALID_ID)
				, EntityNotFoundException.class
				, String.format(USUARIO_NAO_ENCONTRADO_BY_ID,INVALID_ID));    
	}
	
	@Test
	@DisplayName("1.3 Quando procurar por id, deve retornar QueryUserDTO")
    void whenFindByIdThenShouldReturnQueryUserDTO() {
		//when
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        
        //then
        QueryUserDTO foundedUser = userService.findById(ID).getDto();
        assertAll(
        		"Check if user was returned"
        		, () -> assertNotNull(foundedUser)
        		, () -> assertEquals(QueryUserDTO.class,foundedUser.getClass())
        		, () -> assertEquals(ID, 				foundedUser.getId())
        		, () -> assertEquals(USERNAME, 			foundedUser.getUsername())
        		, () -> assertEquals(ROLES_NAMES,		foundedUser.getRolesNames())
        );
    }
	
	@Test
	@DisplayName("2. Quando tentar carregar usuário por username, deve retornar UserDetais")
    void whenLoadUserByUsernameThenShouldReturnUserDetais() {
		//when
        when(userRepository.findByUsernameIgnoreCase(USERNAME)).thenReturn(Optional.of(user));
        
        //then
        UserDetails foundedUser = userService.loadUserByUsername(USERNAME);
        assertAll(
        		"Check if UserDetails was returned"
        		, () -> assertNotNull(foundedUser)
        		, () -> assertInstanceOf(UserDetails.class, foundedUser)
        		, () -> assertEquals(USERNAME,			 	foundedUser.getUsername())
        		, () -> assertEquals(PASSWORD, 				foundedUser.getPassword())
        		, () -> assertEquals(Set.of(role), 		foundedUser.getAuthorities())
        );
    }
	
	@Test
	@DisplayName("2.2.  Quando tentar carregar usuário por username inválido, deve lançar UsernameNotFoundException")
    void whenLoadUserByInvalidUsernameThenShouldThrowsUsernameNotFoundException() {
		//when
		when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.empty());
		
		//then	
		assertThrows(UsernameNotFoundException.class, ()-> userService.loadUserByUsername(INVALID_USERNAME));    
	}
	
	@Test
	@DisplayName("3. Quando procurar todos os usuários, deve retornar paginação de QueryUserDTO")
    void whenSearchingForAllUsersShouldReturnPaginationOfQueryUserDTO() {
		//given
		Page<User> userPage = new PageImpl<>(List.of(user));
		Pageable pageable = PageRequest.of(0, 8);
		QueryUserDTO expectedUserDTO = UserUtils.createFakeQueryUserDTO();
		
		//when
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);
        
        
        //then
        Page<QueryUserDTO> result = userService.findAll(pageable);
        assertAll(
        		"Check if Page<QueryUserDTO> was returned"
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(Page.class, result)
        		, () -> assertInstanceOf(QueryUserDTO.class, result.getContent().get(0))
        		, () -> assertThat(result.getContent()).contains(expectedUserDTO)
        );
    }
	
	@Test
	@DisplayName("4. Quando criar usuário com RecordUserDTO, deve retornar QueryUserDTO")
    void whenCreateUserWithRecordUserDTOShouldReturnQueryUserDTO() {
		//given
		Role role_admin = new Role(1L, ROLE_ADMIN, new ArrayList<>());
		
		//when
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleService.findByName(ROLE_ADMIN)).thenReturn(role_admin);
        
        //then
        QueryUserDTO result = userService.create(givenUserDTO).getDto();
        assertAll(
        		"Check if user saved and QueryUserDTO was returned"
        		, () -> verify(userRepository,times(1)).save(any(User.class))
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(QueryUserDTO.class, result)
        		, () -> assertEquals(ID,			result.getId())
        		, () -> assertEquals(USERNAME,		result.getUsername())
        		, () -> assertEquals(ROLES_NAMES, 	result.getRolesNames())
        );
    }
	
	@Test
	@DisplayName("4.2 Quando criar usuário com RecordUserDTO, deve retornar User")
    void whenCreateUserWithRecordUserDTOShouldReturnUser() {
		//given
		Role role_admin = new Role(1L, ROLE_ADMIN, new ArrayList<>());
		
		//when
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleService.findByName(ROLE_ADMIN)).thenReturn(role_admin);
        
        //then
        User result = userService.create(givenUserDTO, ENTITY).getEntity();
        assertAll(
        		"Check if user saved and User was returned"
        		, () -> verify(userRepository,times(1)).save(any(User.class))
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(User.class, result)
        		, () -> assertEquals(ID,			result.getId())
        		, () -> assertEquals(USERNAME,		result.getUsername())
        		, () -> assertEquals(ROLES,			result.getRoles())
        );
    }
	
	@Test
	@DisplayName("4.3. Quando criar usuário com nome já existente, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenCreateUserWithExistingNameShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.of(user));
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> userService.create(givenUserDTO)
				, DataIntegrityViolationException.class
				, USERNAME_JA_CADASTRADO);
    }
	
	@Test
	@DisplayName("5. Quando atualizar usuário existente, deve retornarQueryUserDTO")
    void whenUpdateExistingUserShouldReturnQueryUserDTO() {
		//when
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        
        //then
        QueryUserDTO result = userService.update(ID, givenUserDTO);
        assertAll(
        		"Check if user updated and QueryUserDTO was returned"
        		, () -> verify(userRepository,times(1)).save(any(User.class))
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(QueryUserDTO.class, result)
        		, () -> assertEquals(ID,			result.getId())
        		, () -> assertEquals(USERNAME,		result.getUsername())
        		, () -> assertEquals(ROLES_NAMES, 	result.getRolesNames())
        );
    }
	
	@Test
	@DisplayName("5.2. Quando atualizar usuário para um nome já existente em outro usuário, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenUpdateUserWithExistingNameInAnotherUserShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		givenUserDTO.setUsername(INVALID_USERNAME);
		
		//when
		when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.of(user));
		doReturn(new EntityDtoBox<>(user, ENTITY)).when(userService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> userService.update(ID, givenUserDTO)
				, DataIntegrityViolationException.class
				, USERNAME_JA_CADASTRADO);
    }
	
	@Test
	@DisplayName("5.3. Quando atualizar usuário para um nome diferente, deve retornarQueryUserDTO")
    void whenUpdateExistingUserForAnotherUUsernameShouldReturnQueryUserDTO() {
		//given
		givenUserDTO.setUsername(NEW_USERNAME);
		
		//when
		when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.empty());
		doReturn(new EntityDtoBox<>(user, ENTITY)).when(userService).findById(ID, ENTITY);
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		//then
        QueryUserDTO result = userService.update(ID, givenUserDTO);
        assertAll(
        		"Check if user updated and QueryUserDTO was returned"
        		, () -> verify(userRepository,times(1)).save(any(User.class))
        		, () -> assertNotNull(result)
        		, () -> assertInstanceOf(QueryUserDTO.class, result)
        		, () -> assertEquals(ID,			result.getId())
        		, () -> assertEquals(USERNAME,		result.getUsername())
        		, () -> assertEquals(ROLES_NAMES, 	result.getRolesNames())
        );
    }
	
	
	@Test
	@DisplayName("6. Quando excluir usuário existente, deve ser excluído")
    void whenDeleteExistingUserShouldBeDeleted() {
		//given
		user.setPerson(null);
		
		//when
		doReturn(new EntityDtoBox<>(user, ENTITY)).when(userService).findById(ID, ENTITY);
		
		//then
        userService.deleteById(ID);
        assertAll(
        		"Check if deleteById runs"
            	,() -> verify(userRepository, times(1)).deleteById(anyLong())
        );
    }
	
	@Test
	@DisplayName("6.2. Quando tentar excluir usuário com vínculo com pessoa, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteUserWithPersonRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//when
		doReturn(new EntityDtoBox<>(user, ENTITY)).when(userService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> userService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_PESSOA
						, ID, user.getPerson().getId()));        
    }
	
	@Test
	@DisplayName("6.3. Quando tentar excluir usuário com vínculo de atendimento, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenTryDeleteUserWithAttendanceRelationshipShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		user.setPerson(null);
		user.setAttendances(ATTENDANCES);
		
		//when
		doReturn(new EntityDtoBox<>(user, ENTITY)).when(userService).findById(ID, ENTITY);
		
		//then
		assertThrowsExceptionWithCorrectMessage(() -> userService.deleteById(ID)
				, DataIntegrityViolationException.class
				, String.format(USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_ATENDIMENTO
						, ID
						, user.getAttendances().stream()
							.map(attendance -> attendance.getId().toString())
							.collect(Collectors.joining(","))));           
    }
	
	@Test
	@DisplayName("7. Deve adicionar perfil para o usuário")
    void shouldAddRoleToUser() {
		//when
		when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(roleService.findByName(any(RoleName.class))).thenReturn(role);
        
        //then
        userService.addRoleToUser(ID, ROLE_ADMIN);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(userService, times(1)).findById(ID,ENTITY)
            	,() -> verify(roleService, times(1)).findByName(any(RoleName.class))
        );
    }
	
	@Test
	@DisplayName("8. Deve remover perfil para o usuário")
    void shouldRemoveRoleToUser() {
		//when
		when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(roleService.findByName(any(RoleName.class))).thenReturn(role);
        
        //then
        userService.removeRoleToUser(ID, ROLE_ADMIN);
        assertAll(
        		"Check if findById and delete runs"
        		,() -> verify(userService, times(1)).findById(ID,ENTITY)
            	,() -> verify(roleService, times(1)).findByName(any(RoleName.class))
        );
    }
	
	@Test
	@DisplayName("9. Deve trocar senha do usuário")
    void shouldHandlePasswordOfTheUser() {
		//given
		user.setPassword(OLD_PASSWORD_ENCODED);
		PasswordUserDTO passwordUserDTO = new PasswordUserDTO(OLD_PASSWORD, NEW_PASSWORD);
		
		//when
		doReturn(user).when(userService).findByUsername(user.getUsername());
		when(userRepository.save(any(User.class))).thenReturn(user);
        
        //then
        userService.handlePassword(user.getUsername(), passwordUserDTO);
        assertAll(
        		"Check if findByUsername and save runs"
        		,() -> verify(userService, times(1)).findByUsername(user.getUsername())
            	,() -> verify(userRepository, times(1)).save(any(User.class))
        );
    }
	
	@Test
	@DisplayName("9. Quando senha atual informada é diferente, deve lançar DataIntegrityViolationException com mensagem correta")
    void whenOldPasswordIsNotSameShouldThrowsDataIntegrityViolationExceptionWithCorrectMessage() {
		//given
		user.setPassword(NEW_PASSWORD_ENCODED);
		PasswordUserDTO passwordUserDTO = new PasswordUserDTO(OLD_PASSWORD, NEW_PASSWORD);
		
		//when
		doReturn(user).when(userService).findByUsername(user.getUsername());
        
        //then
        assertThrowsExceptionWithCorrectMessage(() -> userService.handlePassword(user.getUsername(), passwordUserDTO)
        		, DataIntegrityViolationException.class
        		, USUARIO_OU_SENHA_INVALIDOS);
    }

}
