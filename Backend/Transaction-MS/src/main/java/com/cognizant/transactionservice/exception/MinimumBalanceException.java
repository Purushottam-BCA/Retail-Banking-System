package com.cognizant.transactionservice.exception;

public class MinimumBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MinimumBalanceException(String message) {
		super(message);
	}

}
