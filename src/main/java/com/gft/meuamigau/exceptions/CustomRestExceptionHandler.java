package com.gft.meuamigau.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gft.meuamigau.dtos.ApiErrorDTO;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler( { MeuAmigAuException.class } )
	public ResponseEntity<ApiErrorDTO> handleLojaException(MeuAmigAuException e, HttpServletRequest request){
		
		ApiErrorDTO err = new ApiErrorDTO();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setError("Erro no sistema :(");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler( { EntityNotFoundException.class } )
	public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request){
		
		ApiErrorDTO err = new ApiErrorDTO();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler( { DataIntegrityViolationException.class } )
	public ResponseEntity<ApiErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){
		
		ApiErrorDTO err = new ApiErrorDTO();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError("Data Integrity Violation");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler( { BadRequestBodyException.class } )
	public ResponseEntity<ApiErrorDTO> handleBadRequestBodyException(BadRequestBodyException e, HttpServletRequest request){
		
		ApiErrorDTO err = new ApiErrorDTO();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("Bad Request Body");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
	@ExceptionHandler( { ConnectionExternalApiException.class } )
	public ResponseEntity<ApiErrorDTO> handleBadRequestBodyException(ConnectionExternalApiException e, HttpServletRequest request){
		
		ApiErrorDTO err = new ApiErrorDTO();
		err.setTimestamp(Instant.now());
		err.setStatus(e.getReturnedHttpStatusError());
		err.setError("An error occured in request information");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(err.getStatus()).body(err);
		
	}
	
}
