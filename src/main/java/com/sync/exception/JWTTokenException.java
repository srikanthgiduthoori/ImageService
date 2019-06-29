package com.sync.exception;

public class JWTTokenException extends ImageServiceBaseException{

	public JWTTokenException(String errorMessage) {
		super(errorMessage);
	}

	public JWTTokenException(String errorMessage, String errorSummary) {
		super(errorMessage,errorSummary);
	}

	
	
}
