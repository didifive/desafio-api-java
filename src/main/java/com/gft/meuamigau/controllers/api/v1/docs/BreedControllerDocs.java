package com.gft.meuamigau.controllers.api.v1.docs;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_ADMIN;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_USER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_VET;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_BEARER_AUTH;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_GET;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLER_TAG;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_BY_NAME_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.BREED_CONTROLLER_IMAGE_BY_ID_OPERATION_SUMMARY;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.gft.meuamigau.dtos.ApiErrorDTO;
import com.gft.meuamigau.dtos.breed.ImageBreedDTO;
import com.gft.meuamigau.dtos.breed.QueryBreedDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = SECURITY_SCHEME_BEARER_AUTH)
@Tag(name = BREED_CONTROLER_TAG)
public interface BreedControllerDocs {

	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = BREED_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
			, description = BREED_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = BREED_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = BREED_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryBreedDTO>> findAll(@Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_SUMMARY
			, description = BREED_CONTROLLER_FIND_ALL_BY_NAME_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = BREED_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = BREED_CONTROLLER_FIND_ALL_BY_NAME_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = BREED_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryBreedDTO>> findAllByName(String name
    		, @Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = BREED_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
			, description = BREED_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
        	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
        	, name = "id"
        	, description = BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
        	, example = BREED_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = BREED_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = BREED_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = BREED_CONTROLLER_FIND_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<QueryBreedDTO> findById(Long id);
	
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = BREED_CONTROLLER_IMAGE_BY_ID_OPERATION_SUMMARY
			, description = BREED_CONTROLLER_FIND_IMAGE_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
        	, schema = @Schema(type = SCHEMA_TYPE_STRING)
        	, name = "id"
        	, description = BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_DESCRIPTION
        	, example = BREED_CONTROLLER_FIND_IMAGE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = BREED_CONTROLLER_FIND_IMAGE_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = BREED_CONTROLLER_FIND_IMAGE_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = BREED_CONTROLLER_FIND_IMAGE_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<ImageBreedDTO> findImageById(String id);
}
