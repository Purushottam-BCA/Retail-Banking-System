package com.cognizant.customerservice.exception;

public class ConsumerAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -2862505141325062716L;

	public ConsumerAlreadyExistException() {
		super();
	}

	public ConsumerAlreadyExistException(String message) {
		super(message);
	}

}
