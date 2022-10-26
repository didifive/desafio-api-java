package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES_BY_CLIENTS_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES_BY_DOGS_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES_BY_VETS_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_ATTENDANCES_ID;

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

import com.gft.meuamigau.controllers.api.v1.docs.AttendanceControllerDocs;
import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;
import com.gft.meuamigau.exceptions.BadRequestBodyException;
import com.gft.meuamigau.services.AttendanceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_ATTENDANCES)
public class AttendanceController implements AttendanceControllerDocs{
	
	private static final String HAS_AUTHORITY_ROLE_ADMIN = "hasAuthority('ROLE_ADMIN')";
	private static final String HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN = "hasAnyAuthority ('ROLE_VET', 'ROLE_USER', 'ROLE_ADMIN')";
	private static final String HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN_OR_ROLE_CLIENT_ONLY_OWN_USER =
			HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN
			+ "OR (hasAuthority('ROLE_CLIENT') AND principal.getPerson.getClient.getId == #id)";
	
	private final AttendanceService attendanceService;
	
	@PostMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<QueryAttendanceDTO> create(
			@RequestBody @Valid RecordAttendanceDTO dto
			, BindingResult bindingResult) {
		
		checkBindingResult(bindingResult);
		
		QueryAttendanceDTO savedDog = attendanceService.create(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
												.buildAndExpand(savedDog.getId()).toUri();
		
		return ResponseEntity.created(uri).body(savedDog);
	}

	@GetMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryAttendanceDTO>> findAll(
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(attendanceService.findAll(pageable));
	}
	
	@GetMapping(PATH_ATTENDANCES_BY_CLIENTS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN_OR_ROLE_CLIENT_ONLY_OWN_USER)
	@Override
	public ResponseEntity<Page<QueryAttendanceDTO>> findAllByClient(@PathVariable Long id
			, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(attendanceService.findAllByClientId(id, pageable));
	}
	
	@GetMapping(PATH_ATTENDANCES_BY_VETS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryAttendanceDTO>> findAllByVet(@PathVariable Long id
			, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(attendanceService.findAllByVetId(id, pageable));
	}
	
	@GetMapping(PATH_ATTENDANCES_BY_DOGS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryAttendanceDTO>> findAllByDog(@PathVariable Long id
			, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(attendanceService.findAllByDogId(id, pageable));
	}
	
	@GetMapping(PATH_ATTENDANCES_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<QueryAttendanceDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(attendanceService.findById(id).getDto());
	}

	@PutMapping(PATH_ATTENDANCES_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<QueryAttendanceDTO> update(@PathVariable Long id
													,@RequestBody RecordAttendanceDTO recordDogDTO
													, BindingResult bindingResult) {
		checkBindingResult(bindingResult);
		
		QueryAttendanceDTO updatedDog = attendanceService.update(id, recordDogDTO);
		
		return ResponseEntity.ok().body(updatedDog);
	}

	@DeleteMapping(PATH_ATTENDANCES_ID)
	@PreAuthorize(HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public ResponseEntity<QueryAttendanceDTO> deleteById(@PathVariable Long id) {
		attendanceService.deleteById(id);		
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
