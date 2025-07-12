package com.wtt.TimetraxRestApis.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// This class can be used to handle exceptions globally across the application.
	// You can define methods annotated with @ExceptionHandler to handle specific
	// exceptions.
	// For example, you can handle ResourceNotFound exceptions here and return a
	// custom response.


	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false),
				"RESOURCE_NOT_FOUND");
		return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.NOT_FOUND);

		
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(EmailAlreadyExistException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),			
				ex.getMessage(),
				request.getDescription(false),
				"EMAIL_ALREADY_EXISTS");
		System.out.println("EmailAlreadyExistException: " + ex.getMessage());
		return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.BAD_REQUEST);

		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false),
				"INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false),
				"CUSTOMER_ALREADY_EXISS");
		return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.BAD_REQUEST);

		
	}
	
	@ExceptionHandler(ProjectAlreadyException.class)
	public ResponseEntity<ErrorDetails> handleProjectAlreadyException(ProjectAlreadyException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false),
				"PROJECT_ALREADY_EXIS");
		return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.BAD_REQUEST);

		
	}


}
