package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_PEOPLE;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_PEOPLE_HANDLE_USER_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_PEOPLE_HANDLE_USER_USERNAME;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_PEOPLE_ID;

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

import com.gft.meuamigau.controllers.api.v1.docs.PersonControllerDocs;
import com.gft.meuamigau.dtos.person.DetailsPersonDTO;
import com.gft.meuamigau.dtos.person.QueryPersonDTO;
import com.gft.meuamigau.dtos.person.RecordPersonDTO;
import com.gft.meuamigau.exceptions.BadRequestBodyException;
import com.gft.meuamigau.services.PersonService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_PEOPLE)
public class PersonController implements PersonControllerDocs{
	
	private static final String HAS_ANY_AUTHORITY_ROLES_USER_ADMIN_OR_ROLE_PERSON_ONLY_OWN_USER = "hasAnyAuthority ('ROLE_USER', 'ROLE_ADMIN') "
			+ "OR (hasAuthority('ROLE_PERSON') AND principal.getPerson.getId == #id)";
	private static final String HAS_ANY_AUTHORITY_ROLES_USER_ADMIN = "hasAnyAuthority ('ROLE_USER', 'ROLE_ADMIN')";
	private static final String HAS_AUTHORITY_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
	
	private final PersonService personService;
	
	@PostMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN)
	@Override
	public ResponseEntity<QueryPersonDTO> create(
			@RequestBody @Valid RecordPersonDTO dto
			, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new BadRequestBodyException(
					bindingResult.getFieldErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining("||")));
		}
		
		QueryPersonDTO savedPerson = personService.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												.buildAndExpand(savedPerson.getId()).toUri();
		
		return ResponseEntity.created(uri).body(savedPerson);
	}

	@GetMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryPersonDTO>> findAll(
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pegeable) {
		return ResponseEntity.ok().body(personService.findAll(pegeable));
	}
	
	@GetMapping(PATH_PEOPLE_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN_OR_ROLE_PERSON_ONLY_OWN_USER)
	@Override
	public ResponseEntity<DetailsPersonDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(personService.findById(id).getDto());
	}

	@PutMapping(PATH_PEOPLE_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<QueryPersonDTO> update(@PathVariable Long id
													,@RequestBody RecordPersonDTO recordPersonDTO
													, BindingResult bindingResult) {
		QueryPersonDTO updatedPerson = personService.update(id, recordPersonDTO);
		return ResponseEntity.ok().body(updatedPerson);
	}
	
	@PatchMapping(PATH_PEOPLE_HANDLE_USER_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<DetailsPersonDTO> handleUser(
			@PathVariable Long id
			, @PathVariable Long newUserId) {
		personService.handleUser(id, newUserId);		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(PATH_PEOPLE_HANDLE_USER_USERNAME)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<DetailsPersonDTO> handleUser(
			@PathVariable Long id
			, @PathVariable String username) {
		personService.handleUser(id, username);		
		return ResponseEntity.noContent().build();
	}


	@DeleteMapping(PATH_PEOPLE_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<DetailsPersonDTO> deleteById(@PathVariable Long id) {
		personService.deleteById(id);		
		return ResponseEntity.noContent().build();
	}

}
