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
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLER_TAG;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_201_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_400_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_CREATE_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_400_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_403_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_404_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_409_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.gft.meuamigau.dtos.ApiErrorDTO;
import com.gft.meuamigau.dtos.attendance.QueryAttendanceDTO;
import com.gft.meuamigau.dtos.attendance.RecordAttendanceDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = SECURITY_SCHEME_BEARER_AUTH)
@Tag(name = ATTENDANCE_CONTROLER_TAG)
public interface AttendanceControllerDocs {

	@Tag(name=TAG_POST)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_CREATE_OPERATION_SUMMARY
					, description = ATTENDANCE_CONTROLLER_CREATE_OPERATION_DESCRIPTION)
	@ApiResponse(responseCode = "201"
    				, description = ATTENDANCE_CONTROLLER_CREATE_201_DESCRIPTION)
    @ApiResponse(responseCode = "400"
                	, description = ATTENDANCE_CONTROLLER_CREATE_400_DESCRIPTION
                	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
    				, description = ATTENDANCE_CONTROLLER_CREATE_403_DESCRIPTION
    				, content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "409"
                	, description = ATTENDANCE_CONTROLLER_CREATE_409_DESCRIPTION
                	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<QueryAttendanceDTO> create(RecordAttendanceDTO recordDogDTO
									, BindingResult bindingResult);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_FIND_ALL_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryAttendanceDTO>> findAll(@Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_CLIENT_ONLY_OWN_USER) @Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_CLIENT_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryAttendanceDTO>> findAllByClient(Long id
    		, @Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_VET_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryAttendanceDTO>> findAllByVet(Long id
    		, @Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = CLIENT_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
				, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
				, name = "size"
				, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION
				, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "page"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE)
	@Parameter(in = ParameterIn.QUERY
            	, schema = @Schema(type = SCHEMA_TYPE_STRING)
            	, name = "sort"
            	, description = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_FIND_ALL_BY_DOG_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
    ResponseEntity<Page<QueryAttendanceDTO>> findAllByDog(Long id
    		, @Parameter(hidden = true)Pageable pegeable);
	
	@Tag(name=TAG_GET)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
        	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
        	, name = "id"
        	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION
        	, example = ATTENDANCE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_200_DESCRIPTION)
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = ATTENDANCE_CONTROLLER_FIND_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	ResponseEntity<QueryAttendanceDTO> findById(Long id);
	
	@Tag(name=TAG_PUT)
	@Tag(name=ROLE_VET) @Tag(name=ROLE_USER) @Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_UPDATE_OPERATION_SUMMARY
    			, description = ATTENDANCE_CONTROLLER_UPDATE_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "200"
    			, description = ATTENDANCE_CONTROLLER_UPDATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "400"
            	, description = ATTENDANCE_CONTROLLER_UPDATE_400_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "403"
    			, description = ATTENDANCE_CONTROLLER_UPDATE_403_DESCRIPTION
    			, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = ATTENDANCE_CONTROLLER_UPDATE_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    @ApiResponse(responseCode = "409"
            	, description = ATTENDANCE_CONTROLLER_UPDATE_409_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<QueryAttendanceDTO> update(Long id
    							, RecordAttendanceDTO recordDogDTO
    							, BindingResult bindingResult);

	@Tag(name=TAG_DELETE)
	@Tag(name=ROLE_ADMIN)
	@Operation(summary = ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY
			, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION)
	@Parameter(in = ParameterIn.PATH
            	, schema = @Schema(type = SCHEMA_TYPE_INTEGER)
            	, name = "id"
            	, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION
            	, example = ATTENDANCE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE)
    @ApiResponse(responseCode = "204"
    			, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION
				, content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "403"
            	, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_403_DESCRIPTION
            	, content = @Content(schema = @Schema(hidden = true)))
	@ApiResponse(responseCode = "404"
            	, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_404_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
	@ApiResponse(responseCode = "409"
            	, description = ATTENDANCE_CONTROLLER_DELETE_BY_ID_409_DESCRIPTION
            	, content = @Content(schema = @Schema(implementation = ApiErrorDTO.class)))
    ResponseEntity<QueryAttendanceDTO> deleteById(Long id);
}
