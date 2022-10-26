package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_USERS;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_USERS_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_USERS_PASS_USERNAME;

import java.net.URI;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.meuamigau.controllers.api.v1.docs.UserControllerDocs;
import com.gft.meuamigau.dtos.user.PasswordUserDTO;
import com.gft.meuamigau.dtos.user.QueryUserDTO;
import com.gft.meuamigau.dtos.user.RecordUserDTO;
import com.gft.meuamigau.exceptions.BadRequestBodyException;
import com.gft.meuamigau.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_USERS)
public class UserController implements UserControllerDocs{
	
	private static final String HAS_AUTHORITY_ROLE_ADMIN_OR_ONLY_OWN_USER = "hasAuthority('ROLE_ADMIN') "
			+ "OR principal.getId == #id";
	private static final String HAS_AUTHORITY_ROLE_ADMIN_BUT_NOT_OWN_USER = "hasAuthority('ROLE_ADMIN') AND principal.getId != #id";
	private static final String HAS_AUTHORITY_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
	private static final String ONLY_OWN_USER = "principal.getUsername == #username";
	
	private final UserService userService;
	
	@PostMapping
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<QueryUserDTO> create(
			@RequestBody @Valid RecordUserDTO dto
			, BindingResult bindingResult) {
		
		this.checkBindingResult(bindingResult);
		
		QueryUserDTO savedUser = userService.create(dto).getDto();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(uri).body(savedUser);
	}

	@GetMapping
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<Page<QueryUserDTO>> findAll(
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pegeable) {
		return ResponseEntity.ok().body(userService.findAll(pegeable));
	}
	
	@GetMapping(PATH_USERS_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN_OR_ONLY_OWN_USER)
	@Override
	public ResponseEntity<QueryUserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.findById(id).getDto());
	}

	@PutMapping(PATH_USERS_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<QueryUserDTO> update(@PathVariable Long id
													, @RequestBody @Valid RecordUserDTO recordUserDTO
													, BindingResult bindingResult) {
		this.checkBindingResult(bindingResult);
		
		QueryUserDTO updatedUser = userService.update(id, recordUserDTO);
		
		return ResponseEntity.ok().body(updatedUser);
	}

	@DeleteMapping(PATH_USERS_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN_BUT_NOT_OWN_USER)
	@Override
	public ResponseEntity<QueryUserDTO> deleteById(@PathVariable Long id) {
		
		userService.deleteById(id);		
		
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(PATH_USERS_PASS_USERNAME)
	@PreAuthorize(ONLY_OWN_USER)
	@Override
	public ResponseEntity<QueryUserDTO> handlePassword(@PathVariable String username
			, @RequestBody @Valid PasswordUserDTO passwordUserDTO
			, BindingResult bindingResult) {
		
		this.checkBindingResult(bindingResult);
		
		userService.handlePassword(username, passwordUserDTO);
		
		return ResponseEntity.noContent().build();
	}

	private void checkBindingResult(BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new BadRequestBodyException(
					bindingResult.getFieldErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining("||")));
		}
	}

}
