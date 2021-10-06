package com.alkemy.ar.controller;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.exception.InternalServerException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value= {EntityNotFoundException.class}) /*errores.class*/
	protected ResponseEntity<Object> nombreDelMetodo(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción:" + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value=ContinentException.class) /*errores.class*/
	protected ResponseEntity<Object> throwContinentException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value=InternalServerException.class)
	protected ResponseEntity<Object> throwInternalServerException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
}
