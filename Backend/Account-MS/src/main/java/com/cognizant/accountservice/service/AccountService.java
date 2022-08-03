package com.cognizant.accountservice.service;

import java.text.ParseException;
import java.util.List;

import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.model.Statement;

public interface AccountService {

	public AccountCreationStatus createAccount(String customerId, Account account);

	public List<Account> getCustomerAccount(String token, String customerId);

	public Account getAccount(long accountId);

	public AuthenticationResponse hasPermission(String token);

	public AuthenticationResponse hasEmployeePermission(String token);

	public AuthenticationResponse hasCustomerPermission(String token);

	public Account updateDepositBalance(AccountInput accountInput);

	public Account updateBalance(AccountInput accountInput);

	public List<Account> getAllAccounts();

	List<Statement> getAccountStatement(long accountId);

	List<Statement> getAccountStatement(long accountId, String from, String to) throws ParseException;

	void updateStatement(Account updatedSourceAccBal, Account updatedTargetAccBal, double amount, String message);

	void updateStatement(AccountInput accInput, Account newUpdateAccBal, String message);

	void deleteCustomer(String id);

}
