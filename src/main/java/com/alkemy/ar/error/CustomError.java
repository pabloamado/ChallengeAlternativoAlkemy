package com.alkemy.ar.error;

public class CustomError {

	private String msgError;
	
	public CustomError(String wrongParametersException) {
		
		this.msgError=wrongParametersException;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	
}
