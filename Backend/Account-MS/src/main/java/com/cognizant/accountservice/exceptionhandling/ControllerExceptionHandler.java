package com.cognizant.accountservice.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.accountservice.model.AccountErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MinimumBalanceException.class)
	public ResponseEntity<AccountErrorResponse> minimumBalanceException(MinimumBalanceException exception,
			WebRequest request) {
		AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Minimum Balance Problem");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<AccountErrorResponse> accountNotFoundException(AccountNotFoundException exception,
			WebRequest request) {
		AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Account not found");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<AccountErrorResponse> accessDeniedException(AccessDeniedException exception,
			WebRequest request) {
		AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Access Denied");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<AccountErrorResponse> globalException(Exception exception, WebRequest request) {
		AccountErrorResponse response = new AccountErrorResponse(LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE,
				exception.getMessage(), "Error occurred");
		return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
	}

}
