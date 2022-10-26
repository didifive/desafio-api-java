package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_CLIENTS;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_CLIENTS_ID;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.meuamigau.controllers.api.v1.docs.ClientControllerDocs;
import com.gft.meuamigau.dtos.client.QueryClientDTO;
import com.gft.meuamigau.dtos.client.RecordClientDTO;
import com.gft.meuamigau.exceptions.BadRequestBodyException;
import com.gft.meuamigau.services.ClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_CLIENTS)
public class ClientController implements ClientControllerDocs{
	
	private static final String HAS_ANY_AUTHORITY_ROLES_USER_ADMIN_OR_ROLE_CLIENT_ONLY_OWN_USER = "hasAnyAuthority ('ROLE_USER', 'ROLE_ADMIN') "
			+ "OR (hasAuthority('ROLE_CLIENT') AND principal.getPerson.getClient.getId == #id)";
	private static final String HAS_ANY_AUTHORITY_ROLES_USER_ADMIN = "hasAnyAuthority ('ROLE_USER', 'ROLE_ADMIN')";
	private static final String HAS_AUTHORITY_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
	
	private final ClientService clientService;
	
	@PostMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN)
	@Override
	public ResponseEntity<QueryClientDTO> create(
			@RequestBody @Valid RecordClientDTO dto
			, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			throw new BadRequestBodyException(
					bindingResult.getFieldErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining("||")));
		}
		
		QueryClientDTO savedClient = clientService.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												.buildAndExpand(savedClient.getId()).toUri();
		
		return ResponseEntity.created(uri).body(savedClient);
	}

	@GetMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryClientDTO>> findAll(
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pegeable) {
		return ResponseEntity.ok().body(clientService.findAll(pegeable));
	}
	
	@GetMapping(PATH_CLIENTS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN_OR_ROLE_CLIENT_ONLY_OWN_USER)
	@Override
	public ResponseEntity<QueryClientDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(clientService.findById(id).getDto());
	}

	@PutMapping(PATH_CLIENTS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_USER_ADMIN)
	@Override
	public ResponseEntity<QueryClientDTO> update(@PathVariable Long id
													,@RequestBody RecordClientDTO recordClientDTO
													, BindingResult bindingResult) {
		QueryClientDTO updatedPerson = clientService.update(id, recordClientDTO);
		return ResponseEntity.ok().body(updatedPerson);
	}

	@DeleteMapping(PATH_CLIENTS_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<QueryClientDTO> deleteById(@PathVariable Long id) {
		clientService.deleteById(id);		
		return ResponseEntity.noContent().build();
	}

}
