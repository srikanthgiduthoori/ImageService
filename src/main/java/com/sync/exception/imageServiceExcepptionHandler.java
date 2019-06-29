package com.sync.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sync.vo.ErrorResponseVO;

@ControllerAdvice
public class imageServiceExcepptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { ImageServiceBaseException.class })
	@ResponseBody
	ErrorResponseVO handleInvalidInputValidationException(HttpServletRequest req, ImageServiceBaseException ex) {
		ErrorResponseVO response = this.constructErrorMessage(ex);
		return response;
	}

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	ErrorResponseVO handleInvalidInputValidationException(HttpServletRequest req, Exception ex) {
		ErrorResponseVO response = this.constructErrorMessage(ex);
		return response;
	}
	private ErrorResponseVO constructErrorMessage(Exception ex) {
		ErrorResponseVO errorResponse = new ErrorResponseVO();
		errorResponse.setErrorMessage(ex.getMessage());
		errorResponse.setErrorSummary(ex.getStackTrace().toString());
		return errorResponse;
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { JWTTokenException.class })
	@ResponseBody
	ErrorResponseVO handleInvalidJWTException(HttpServletRequest req, JWTTokenException ex) {
		ErrorResponseVO response = this.constructErrorMessage(ex);
		return response;
	}


	private ErrorResponseVO constructErrorMessage(ImageServiceBaseException ex) {

		ErrorResponseVO errorResponse = new ErrorResponseVO();
		errorResponse.setErrorMessage(ex.getErrorMessage());
		errorResponse.setErrorSummary(ex.getErrorSummary());
		return errorResponse;
	}

}
