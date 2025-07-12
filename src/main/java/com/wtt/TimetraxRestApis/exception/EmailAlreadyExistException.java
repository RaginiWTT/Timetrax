package com.wtt.TimetraxRestApis.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException {
	
	private String message;
	
	public EmailAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}

}
