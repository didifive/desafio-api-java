package com.gft.meuamigau.controllers.api.v1.docs;

import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.PUBLIC;
import static com.gft.meuamigau.enums.constants.OpenApiAnnotations.TAG_POST;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.AUTHENTICATION_CONTROLER_AUTHENTICATE_200_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.AUTHENTICATION_CONTROLER_AUTHENTICATE_401_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY;
import static com.gft.meuamigau.enums.constants.api.v1.ControllersAnnotationsV1.AUTHENTICATION_CONTROLER_TAG;

import org.springframework.http.ResponseEntity;

import com.gft.meuamigau.dtos.auth.AuthenticationDTO;
import com.gft.meuamigau.dtos.auth.TokenDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = AUTHENTICATION_CONTROLER_TAG)
public interface AuthenticationControllerDocs {	

	@Tag(name=TAG_POST)
	@Tag(name=PUBLIC)
	@Operation(summary = AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY
				, description = AUTHENTICATION_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION)
    @ApiResponse(responseCode = "200"
    				, description = AUTHENTICATION_CONTROLER_AUTHENTICATE_200_DESCRIPTION)
    @ApiResponse(responseCode = "401"
    				, description = AUTHENTICATION_CONTROLER_AUTHENTICATE_401_DESCRIPTION
    				, content = @Content(schema = @Schema(hidden = true)))
	ResponseEntity<TokenDTO> authenticate(AuthenticationDTO authDto);

}
