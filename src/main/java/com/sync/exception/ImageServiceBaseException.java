package com.sync.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ImageServiceBaseException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String errorSummary;
	
	public ImageServiceBaseException() {
		super();
	}
	public ImageServiceBaseException(String errorMessage,String errorSummary) {
		this.errorMessage = errorMessage;
		this.errorSummary = errorSummary;
	}
	public ImageServiceBaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ImageServiceBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	public ImageServiceBaseException(String message) {
		super(message);
	}
	public ImageServiceBaseException(Throwable cause) {
		super(cause);
	}

}
