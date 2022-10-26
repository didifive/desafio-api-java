package com.gft.meuamigau.services;

import static com.gft.meuamigau.enums.EntityDtoBoxType.DTO;
import static com.gft.meuamigau.enums.EntityDtoBoxType.ENTITY;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USERNAME_JA_CADASTRADO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_ENCONTRADO_BY_ID;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_ENCONTRADO_BY_NAME;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_ATENDIMENTO;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_PESSOA;
import static com.gft.meuamigau.enums.constants.ErrorMessages.USUARIO_OU_SENHA_INVALIDOS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.meuamigau.dtos.user.PasswordUserDTO;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.dtos.user.UserMapper;
import com.gft.meuamigau.entities.Role;
import com.gft.meuamigau.entities.User;
import com.gft.meuamigau.enums.EntityDtoBoxType;
import com.gft.meuamigau.enums.RoleName;
import com.gft.meuamigau.exceptions.DataIntegrityViolationException;
import com.gft.meuamigau.exceptions.EntityNotFoundException;
import com.gft.meuamigau.repositories.UserRepository;
import com.gft.meuamigau.utils.EntityDtoBox;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private final RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
	}

	/**
	 * CRUD: Read
	 * Find user by id and returns entity or DTO
	 * @param Long id
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<User, QueryUserDTO>
	 */
	public EntityDtoBox<User, QueryUserDTO> findById(Long idUser, EntityDtoBoxType returnType) {
		User user = userRepository.findById(idUser).orElseThrow(				
			() -> new EntityNotFoundException(
					String.format(USUARIO_NAO_ENCONTRADO_BY_ID, idUser)));
		
		if(returnType.equals(ENTITY)) {
			return new EntityDtoBox<>(user,ENTITY);
		} 
		
		return new EntityDtoBox<>(UserMapper.fromEntity(user),DTO);
	}
	
	/**
	 * CRUD: Read
	 * Find user by id and returns DTO
	 * @param Long id
	 * @return EntityDtoBox<User, QueryUserDTO>
	 */
	public EntityDtoBox<User, QueryUserDTO> findById(Long idUser) {
		return this.findById(idUser, DTO);
	}

	/**
	 * CRUD: Read
	 * Find users and list with pageable content
	 * @param Pageable pageable
	 * @return Page<QueryUserDTO>
	 */
	public Page<QueryUserDTO> findAll(Pageable pageable) {
		Page<User> usersPage = userRepository.findAll(pageable);
		
		List<QueryUserDTO> dtos = usersPage.getContent().stream()
										.map(UserMapper::fromEntity)
										.toList();
		 
		return new PageImpl<>(dtos, pageable, usersPage.getTotalElements());
	}
	
	/**
	 * CRUD: Read
	 * Find users by Username
	 * @param String username
	 * @return User
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsernameIgnoreCase(username).orElseThrow(
				()-> new UsernameNotFoundException(
					String.format(USUARIO_NAO_ENCONTRADO_BY_NAME, username)));
	}

	/**
	 * CRUD: Create
	 * Create user with validation to prevent username repeat
	 * @param RecordUserDTO dto
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<User, QueryUserDTO>
	 */
	public EntityDtoBox<User, QueryUserDTO> create(RecordUserDTO dto, EntityDtoBoxType returnType) {
		this.usernameValid(dto.getUsername());
		
		User savedUser = userRepository.save(userToEntity(dto));
		
		if(returnType.equals(ENTITY)) {
			return new EntityDtoBox<>(savedUser,ENTITY);
		} 
		
		return new EntityDtoBox<>(UserMapper.fromEntity(savedUser),DTO);
	}
	
	/**
	 * CRUD: Create
	 * Create user with validation to prevent username repeat
	 * @param RecordUserDTO dto
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<User, QueryUserDTO> DTO
	 */
	public EntityDtoBox<User, QueryUserDTO> create(RecordUserDTO dto) {
		return this.create(dto, DTO);
	}

	/**
	 * CRUD: Update
	 * Update user with validation if modificate te username to prevent username repeat
	 * @param RecordUserDTO dto
	 * @param EntityDtoBoxType returnType
	 * @return EntityDtoBox<User, QueryUserDTO>
	 */
	public QueryUserDTO update(Long id, RecordUserDTO dto) {		
		User existingUser = this.findById(id,ENTITY).getEntity();
		
		if(!dto.getUsername().equals(existingUser.getUsername()))
			usernameValid(dto.getUsername());
		
		User currentUser = userToEntity(dto);
		currentUser.setId(existingUser.getId());
		
		User updatedUser =  userRepository.save(currentUser);
		
		return UserMapper.fromEntity(updatedUser);
	}
	
	/**
	 * CRUD: Update
	 * Update user password
	 * @param String username
	 * @param PasswordUserDTO dto
	 * @return void
	 */
	public void handlePassword(String username, PasswordUserDTO dto) {		
		
		User existingUser = this.findByUsername(username);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		if(!bCryptPasswordEncoder.matches(dto.getOldPassword(), existingUser.getPassword()))
			throw new DataIntegrityViolationException(USUARIO_OU_SENHA_INVALIDOS);
		
		existingUser.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));
		
		userRepository.save(existingUser);
	}
	
	/**
	 * CRUD: Delete
	 * Delete user by Id. It's check relations with Person and Attendances before delete.
	 * @param Long id
	 * @return void
	 */
	public void deleteById(Long id) {
		User user = this.findById(id, ENTITY).getEntity();
		
		if (user.getPerson() != null) 
			throw new DataIntegrityViolationException(
					String.format(USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_PESSOA
							, id, user.getPerson().getId()));
		
		if (!user.getAttendances().isEmpty()) 
			throw new DataIntegrityViolationException(
					String.format(USUARIO_NAO_PODE_SER_EXCLUIDO_POR_RELACAO_COM_ATENDIMENTO
							, id
							, user.getAttendances().stream()
    							.map(attendance -> attendance.getId().toString())
    							.collect(Collectors.joining(","))));
		
		userRepository.deleteById(id);		
	}
	
	/**
	 * CRUD: Update
	 * Add a Role to User
	 * @paramLong id
	 * @param RoleName roleName
	 * @return void
	 */
	public void addRoleToUser(Long id, RoleName roleName) {
		User user = this.findById(id,ENTITY).getEntity();
		Role role = roleService.findByName(roleName);
		
		Set<Role> roles = new HashSet<>();
		roles.addAll(user.getRoles());
		roles.add(role);
		
		user.setRoles(roles);
		
		userRepository.save(user);
	}
	
	/**
	 * CRUD: Update
	 * Remove a Role to User
	 * @paramLong id
	 * @param RoleName roleName
	 * @return void
	 */
	public void removeRoleToUser(Long id, RoleName roleName) {
		User user = this.findById(id,ENTITY).getEntity();
		Role role = roleService.findByName(roleName);
		
		Set<Role> roles = new HashSet<>();
		roles.addAll(user.getRoles());
		if(roles.contains(role))
			roles.remove(role);
		
		user.setRoles(roles);
		
		userRepository.save(user);
	}

    /*
     * PRIVATE METHODS  
     */
	private void usernameValid(String username) {
		if (userRepository.findByUsernameIgnoreCase(username).isPresent())
			throw new DataIntegrityViolationException(USERNAME_JA_CADASTRADO);			
	}

	private Set<Role> listRoles(List<RoleName> rolesNames) {
		return rolesNames.stream()
			.map(roleService::findByName)
			.collect(Collectors.toSet());
	}
	
	private User userToEntity(RecordUserDTO dto) {
		User user = UserMapper.fromDTO(dto);
		user.setRoles(listRoles(dto.getRolesNames()));
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return user;
	}

}
