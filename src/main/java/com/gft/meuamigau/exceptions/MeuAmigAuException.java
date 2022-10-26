package com.gft.meuamigau.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeuAmigAuException extends RuntimeException{
	private static final long serialVersionUID = -407779780007065536L;
	
	private final String message;

}