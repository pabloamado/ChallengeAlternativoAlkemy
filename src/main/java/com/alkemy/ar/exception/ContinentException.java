package com.alkemy.ar.exception;

public class ContinentException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ContinentException(String errorMsg) {
		
		super(errorMsg);
	}
}
