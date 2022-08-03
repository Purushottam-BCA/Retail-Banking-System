package com.cognizant.transactionservice.service;

import com.cognizant.transactionservice.models.AccountInput1;
import com.cognizant.transactionservice.models.TransactionInput;

public interface TransactionServiceInterface {

	public boolean makeTransfer(String token, TransactionInput transactionInput);

	public boolean makeWithdraw(String token, AccountInput1 accountInput1);

	public boolean makeDeposit(String token, AccountInput1 accountInput1);

	public boolean makeServiceCharges(String token, AccountInput1 accountInput1);
}
