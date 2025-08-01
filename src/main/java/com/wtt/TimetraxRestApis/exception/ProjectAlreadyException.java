package com.wtt.TimetraxRestApis.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class ProjectAlreadyException extends RuntimeException {
	
	String message;
	
	public ProjectAlreadyException(String message) {
		super(message);
		this.message = message;
	}
	

}
