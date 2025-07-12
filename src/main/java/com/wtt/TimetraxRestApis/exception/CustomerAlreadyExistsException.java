package com.wtt.TimetraxRestApis.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{
	
	private String message;
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	
	}

}
