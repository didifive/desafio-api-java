package com.gft.meuamigau.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(implementation = AddressType.class)
@Getter
@AllArgsConstructor
public enum AddressType {
	
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
	CORRESPONDENCIA("Correspondencia");
	
	private final String description;

}
