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
import com.alkemy.ar.exception.IconException;
import com.alkemy.ar.exception.LocationException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(value=ContinentException.class) /*errores.class*/
	protected ResponseEntity<Object> throwContinentException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción en ContinentService: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value=LocationException.class)
	protected ResponseEntity<Object> throwLocationException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción en LocationService: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	
	}
	
	@ExceptionHandler(value=IconException.class)
	protected ResponseEntity<Object> throwIconException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción en IconService: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	
	}
	
	@ExceptionHandler(value= {EntityNotFoundException.class}) /*errores.class*/
	protected ResponseEntity<Object> nombreDelMetodo(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción no se ha encontrado la entidad:" + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	
	@ExceptionHandler(value=NullPointerException.class)
	protected ResponseEntity<Object> throwNullPointerException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción NullPointerException: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	@ExceptionHandler(value=NumberFormatException.class)
	protected ResponseEntity<Object> throwNumberFormatException(RuntimeException e, WebRequest webRequest){
		
		String message= "Sucedio la siguiente excepción: " + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
}
