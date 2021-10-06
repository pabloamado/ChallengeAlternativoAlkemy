package com.alkemy.ar.exception;

public class LocationException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public LocationException(String errorMsg) {
		
		super(errorMsg);
	}
}
