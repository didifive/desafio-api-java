package com.gft.meuamigau.controllers.api.v1.docs;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_ADMIN;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_CLIENT_ONLY_OWN_USER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_USER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.ROLE_VET;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_INTEGER;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SCHEMA_TYPE_STRING;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.SECURITY_SCHEME_BEARER_AUTH;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_DELETE;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_GET;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_POST;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_PUT;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLER_TAG;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_201_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_400_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_CREATE_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_400_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.DOG_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.gft.meuamigau.dtos.ApiErrorDTO;
import com.gft.meuamigau.dtos.dog.QueryDogDTO;
import com.gft.meuamigau.dtos.dog.RecordDogDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = SECURITY_SCHEME_BEARER_AUTH)
@Tag(name = DOG_CONTROLER_TAG)
public interface DogControllerDocs {

	@Tag(name=TAG_POST)
	@Operation(summary = DOG_CONTROLLER_CREATE_OPERATION_SUMMARY
					, description = DOG_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
	@ApiResponse(responseCode = "201"
    				, description = DOG_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse(responseCode = "400"
                	, description = DOG_CONTROLLER_CREATE_400_DESCRIPTION
                	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
    				, description = DOG_CONTROLLER_CREATE_403_DESCRIPTION
    				, content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "409"
                	, description = DOG_CONTROLLER_CREATE_409_DESCRIPTION
                	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<QueryDogDTO> create(RecordDogDTO recordDogDTO
									, BindingResult bindingResult);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_CLIENT_ONLY_OWN_USER) @Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = DOG_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
			, description = DOG_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = DOG_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = DOG_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryDogDTO>> findAllByClient(Long id
    		, @Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = DOG_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY
			, description = DOG_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = DOG_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = DOG_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = DOG_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryDogDTO>> findAll(@Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = DOG_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
			, description = DOG_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
        	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
        	, name = "id"
        	, description = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
        	, example = DOG_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = DOG_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = DOG_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = DOG_CONTROLLER_FIND_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<QueryDogDTO> findById(Long id);
	
	@Tag(name=TAG_PUT)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = DOG_CONTROLLER_UPDATE_OPERATION_SUMMARY
    			, description = DOG_CONTROLLER_UPDATE_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = DOG_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION
            	, example = DOG_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = DOG_CONTROLLER_UPDATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            	, description = DOG_CONTROLLER_UPDATE_400_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
    			, description = DOG_CONTROLLER_UPDATE_403_DESCRIPTION
    			, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = DOG_CONTROLLER_UPDATE_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            	, description = DOG_CONTROLLER_UPDATE_409_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<QueryDogDTO> update(Long id
    							, RecordDogDTO recordDogDTO
    							, BindingResult bindingResult);

	@Tag(name=TAG_DELETE)
	@Tag(name=ROLE_ADMIN)
	@Operation(summary = DOG_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY
			, description = DOG_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = DOG_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
    			, description = DOG_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION
				, content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403"
            	, description = DOG_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = DOG_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	@ApiResponse(responseCode = "409"
            	, description = DOG_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<QueryDogDTO> deleteById(Long id);
}
