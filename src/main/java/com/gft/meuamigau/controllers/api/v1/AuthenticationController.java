package com.gft.meuamigau.controllers.api.v1;

import static com.gft.meuamigau.enums.constants.api.v1.MappingRoutesV1.PATH_AUTH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.meuamigau.controllers.api.v1.docs.AuthenticationControllerDocs;
import com.gft.meuamigau.dtos.auth.AuthenticationDTO;
import com.gft.meuamigau.dtos.auth.TokenDTO;
import com.gft.meuamigau.services.AuthenticationService;

@RestController
@RequestMapping(PATH_AUTH)
public class AuthenticationController implements AuthenticationControllerDocs{

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthenticationDTO authDto) {
		try {
			return ResponseEntity.ok(authenticationService.autenticar(authDto));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
