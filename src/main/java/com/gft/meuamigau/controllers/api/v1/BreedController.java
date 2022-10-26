package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_BREEDS;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_BREEDS_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_BREEDS_IMAGE_ID;
import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_BREEDS_NAME;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.meuamigau.controllers.api.v1.docs.BreedControllerDocs;
import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;
import com.gft.meuamigau.services.BreedService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(PATH_BREEDS)
public class BreedController implements BreedControllerDocs{
	
	private static final String HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN = "hasAnyAuthority ('ROLE_VET', 'ROLE_USER', 'ROLE_ADMIN')";
	
	private final BreedService breedService;
	
	@GetMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryBreedDTO>> findAll(
			@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(breedService.findAll(pageable));
	}
	
	@GetMapping(PATH_BREEDS_NAME)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<Page<QueryBreedDTO>> findAllByName(@PathVariable String name
			, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok().body(breedService.findByName(name, pageable));
	}
	
	@GetMapping(PATH_BREEDS_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<QueryBreedDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(breedService.findById(id));
	}

	@GetMapping(PATH_BREEDS_IMAGE_ID)
	@PreAuthorize(HAS_ANY_AUTHORITY_ROLES_VET_USER_ADMIN)
	@Override
	public ResponseEntity<ImageBreedDTO> findImageById(@PathVariable String id) {
		return ResponseEntity.ok().body(breedService.findImageById(id));
	}


}
