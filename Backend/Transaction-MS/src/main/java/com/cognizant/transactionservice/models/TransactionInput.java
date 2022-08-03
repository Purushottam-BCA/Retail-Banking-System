package com.cognizant.transactionservice.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInput {

	private AccountInput1 sourceAccount;

	private AccountInput1 targetAccount;

	@Positive(message = "Transfer amount must be greater than 100")
	@Min(value = 1, message = "Amount must be larger than 100")
	private double amount;

	private String reference;

}