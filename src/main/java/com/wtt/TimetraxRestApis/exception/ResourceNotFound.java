package com.wtt.TimetraxRestApis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

	private String ResourceName;
	private String FieldName;
	private int FieldValue;
	
	public ResourceNotFound(String resourceName, String fieldName, int id) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, id));
		ResourceName = resourceName;
		FieldName = fieldName;
		FieldValue = id;
	}
	
	
}
