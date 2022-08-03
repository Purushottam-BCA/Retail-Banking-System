package com.cognizant.accountservice.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.accountservice.exceptionhandling.MinimumBalanceException;
import com.cognizant.accountservice.feignclient.TransactionFeign;
import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.MessageDetails;
import com.cognizant.accountservice.model.Statement;
import com.cognizant.accountservice.model.Transaction;
import com.cognizant.accountservice.model.TransactionInput;
import com.cognizant.accountservice.service.AccountServiceImpl;

@RestController
@CrossOrigin()
public class AccountController { 

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private MessageDetails messageDetails;
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
	private TransactionFeign transactionFeign; 
	
	/*
	 * Getting the account details for given account id
	 */
	@GetMapping("/getAccount/{accountId}")
	public ResponseEntity<Account> getAccount(@RequestHeader("Authorization") String token,@PathVariable long accountId) {
		accountServiceImpl.hasPermission(token);
		Account accountReturnObject = accountServiceImpl.getAccount(accountId);
		LOGGER.info("Account Details Returned Successfully");
		return new ResponseEntity<>(accountReturnObject, HttpStatus.OK);
	}
	
	/*
	 * Creating a new account for an existing customer
	 */
	@PostMapping("/createAccount/{customerId}")
	public ResponseEntity<?> createAccount(@RequestHeader("Authorization") String token,@PathVariable String customerId,@Valid @RequestBody Account account) {
		accountServiceImpl.hasEmployeePermission(token);
		AccountCreationStatus returnObjAccountCreationStatus = accountServiceImpl.createAccount(customerId, account);
		if (returnObjAccountCreationStatus == null) {
			LOGGER.error("Customer Creation Unsuccessful");
			return new ResponseEntity<>("Customer Creation Unsuccessful", HttpStatus.NOT_ACCEPTABLE);
		}
			
		LOGGER.info("Account Created Successfully");
		return new ResponseEntity<>(returnObjAccountCreationStatus, HttpStatus.CREATED);
	}
	
	/*
	 * Getting all the existing account details for the specified customer
	 */
	@GetMapping("/getAccounts/{customerId}")
	public ResponseEntity<List<Account>> getCustomerAccount(@RequestHeader("Authorization") String token,@PathVariable String customerId) {
		accountServiceImpl.hasPermission(token);
		LOGGER.info("Account List Returned");
		return new ResponseEntity<>(accountServiceImpl.getCustomerAccount(token, customerId), HttpStatus.OK);
	}

	/*
	 * Depositing amount in the specified account
	 */
	@PostMapping("/deposit")
	public ResponseEntity<Account> deposit(@RequestHeader("Authorization") String token,@RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		transactionFeign.makeDeposit(token, accInput);
		//Updating the new current balance after deposit
		Account newUpdateAccBal = accountServiceImpl.updateDepositBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		accountServiceImpl.updateStatement(accInput,newUpdateAccBal,"Deposited");
		LOGGER.info("Amount Deposited");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}

	/*
	 * Withdrawing amount from a specified account
	 */
	@PostMapping("/withdraw")
	public ResponseEntity<Account> withdraw(@RequestHeader("Authorization") String token, @RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		try {
			transactionFeign.makeWithdraw(token, accInput);

		} catch (Exception e) {
			LOGGER.error("Minimum Balance 1000 should be maintaind");
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintaind");
		}
		//Updating the new current balance after withdrawal
		Account newUpdateAccBal = accountServiceImpl.updateBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		accountServiceImpl.updateStatement(accInput,newUpdateAccBal,"Withdrawn");
		LOGGER.info("Amount withdrawn successfully");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}
	
	/*
	 * Service charge deduction from the accounts that are having minimum balance
	 */
	@PostMapping("/servicecharge")
	public ResponseEntity<Account> servicecharge(@RequestHeader("Authorization") String token,@RequestBody AccountInput accInput) {
		accountServiceImpl.hasPermission(token);
		try {
			transactionFeign.makeServiceCharges(token, accInput);

		} catch (Exception e) {
			LOGGER.error("Minimum Balance 1000 should be maintaind");
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintaind");
		}
		//Updating the new current balance after service charge deduction
		Account newUpdateAccBal = accountServiceImpl.updateBalance(accInput);
		List<Transaction> list = transactionFeign.getTransactionsByAccId(token, accInput.getAccountId());
		newUpdateAccBal.setTransactions(list);
		accountServiceImpl.updateStatement(accInput,newUpdateAccBal,"Service charge");
		LOGGER.info("Service charge deducted successfully");
		return new ResponseEntity<>(newUpdateAccBal, HttpStatus.OK);
	}

	/*
	 * Transferring amount from one account to another account
	 */
	@PostMapping("/transaction")
	public ResponseEntity<?> transaction(@RequestHeader("Authorization") String token, @RequestBody TransactionInput transInput) {
		accountServiceImpl.hasPermission(token);
		boolean status = true;
		try {
			status = transactionFeign.makeTransfer(token, transInput);

		} catch (Exception e) {
			LOGGER.error("Minimum Balance 1000 should be maintaind");
			throw new MinimumBalanceException("Minimum Balance 1000 should be maintaind");
		}
		if (status == false) {
			return new ResponseEntity<>("Transaction Failed", HttpStatus.NOT_IMPLEMENTED);
		}
		//Updating the source account
		Account updatedSourceAccBal = accountServiceImpl.updateBalance(transInput.getSourceAccount());
		List<Transaction> sourceAcc = transactionFeign.getTransactionsByAccId(token,transInput.getSourceAccount().getAccountId());
		updatedSourceAccBal.setTransactions(sourceAcc);
		
		//Updating the target account
		Account updatedTargetAccBal = accountServiceImpl.updateDepositBalance(transInput.getTargetAccount());
		List<Transaction> targetAcc = transactionFeign.getTransactionsByAccId(token,transInput.getTargetAccount().getAccountId());
		updatedTargetAccBal.setTransactions(targetAcc);
		
		//Updating the account statement
		accountServiceImpl.updateStatement(updatedSourceAccBal,updatedTargetAccBal,transInput.getAmount(),"Transferred");
		LOGGER.info("Transaction completed successfully from Account " + transInput.getSourceAccount().getAccountId()+ " to Target Account " + transInput.getTargetAccount().getAccountId());
		messageDetails.setMessage("Transaction Successfully Done..");
		return new ResponseEntity<>(messageDetails,HttpStatus.OK);
	}

	/*
	 * Checking the current balance of the specified account
	 */
	@PostMapping("/checkBalance")
	public ResponseEntity<Account> checkAccountBalance(@RequestHeader("Authorization") String token,@Valid @RequestBody AccountInput accountInput) {
		accountServiceImpl.hasPermission(token);
		Account account = accountServiceImpl.getAccount(accountInput.getAccountId());
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	/*
	 * Getting all the existing accounts irrespective of customers
	 */
	@GetMapping("/find")
	public ResponseEntity<List<Account>> getAllAccount(@RequestHeader("Authorization") String token) {
		accountServiceImpl.hasPermission(token);
		List<Account> account = accountServiceImpl.getAllAccounts();
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	/*
	 * Deleting the given account from the database
	 */
	@DeleteMapping("deleteCustomer/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String id) {

		System.out.println("Starting deletion of account " + id);
		accountServiceImpl.deleteCustomer(id);
		System.out.println("Deleted");
		return new ResponseEntity<>("Account Deleted successfully", HttpStatus.OK);
	}
	
	/*
	 * Getting account statement of an account for the past one month 
	 */
	@GetMapping("/getAccountStatement/{accountId}")
	public ResponseEntity<List<Statement>> getAccountStatement(@RequestHeader("Authorization") String token,@PathVariable long accountId) {
		accountServiceImpl.hasPermission(token);
		List<Statement> statements = accountServiceImpl.getAccountStatement(accountId);
		LOGGER.info("Account Statement Returned Successfully");
		return new ResponseEntity<>(statements, HttpStatus.OK);
	}
		
	/*
	 * Getting account statement of an account between the given dates
	 */
	@GetMapping("/getAccountStatement/{accountId}/{from}/{to}")
	public ResponseEntity<List<Statement>> getAccountStatement(@RequestHeader("Authorization") String token,@PathVariable long accountId,@PathVariable String from, @PathVariable String to) throws ParseException {
		accountServiceImpl.hasPermission(token);
		List<Statement> statements = accountServiceImpl.getAccountStatement(accountId,from,to);
		LOGGER.info("Account Statement from "+from+" to "+to+" Returned Successfully");
		return new ResponseEntity<>(statements, HttpStatus.OK);
	}
}
