package com.gft.meuamigau.enums;

import com.gft.meuamigau.utils.person.CnpjGroup;
import com.gft.meuamigau.utils.person.CpfGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(implementation = PersonType.class)
@Getter
@AllArgsConstructor
public enum PersonType {
	
	FISICA("Física","CPF","000.000.000-00", CpfGroup.class),
	JURIDICA("Jurídica","CNPJ","00.000.000/0000-00", CnpjGroup.class);
	
	private final String description;
	private final String document;
	private final String mask;
	private final Class<?> groupClass;
	
}
