package com.alkemy.ar.controller;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value= {EntityNotFoundException.class}) /*errores.class*/
	protected ResponseEntity<Object> nombreDelMetodo(RuntimeException e, WebRequest webRequest){
		
		String message= "MENSAJE PERSONALIZADO" + e.getMessage();
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
}
