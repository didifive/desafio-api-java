package com.gft.meuamigau.exceptions;

public class ConnectionExternalApiException extends MeuAmigAuException{
	private static final long serialVersionUID = 7826626511420812455L;

	private final Integer returnedHttpStatusError;
	
	public ConnectionExternalApiException(String message, Integer returnedHttpStatusError) {
		super(message);
		this.returnedHttpStatusError = returnedHttpStatusError;
	}

	public Integer getReturnedHttpStatusError() {
		return this.returnedHttpStatusError;
	}
}
