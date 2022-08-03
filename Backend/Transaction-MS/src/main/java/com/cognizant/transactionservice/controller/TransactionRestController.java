package com.cognizant.transactionservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.transactionservice.feign.AccountFeign;
import com.cognizant.transactionservice.feign.RulesFeign;
import com.cognizant.transactionservice.models.AccountInput1;
import com.cognizant.transactionservice.models.Transaction;
import com.cognizant.transactionservice.models.TransactionInput;
import com.cognizant.transactionservice.repository.TransactionRepository;
import com.cognizant.transactionservice.service.TransactionServiceInterface;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin()
public class TransactionRestController {


	
	@Autowired
	AccountFeign accountFeign;

	@Autowired
	RulesFeign rulesFeign;

	@Autowired
	TransactionRepository transRepo;

	@Autowired
	TransactionServiceInterface transactionService;

	@PostMapping(value = "/transactions")
	public boolean makeTransfer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody TransactionInput transactionInput) {
		log.info("inside transaction method");
		if (transactionInput != null) {
			boolean isComplete = transactionService.makeTransfer(token, transactionInput);

			return isComplete;
		} else {
			return false;
		}
	}
     

	@GetMapping(value = "/getAllTransByAccId/{id}")
	public List<Transaction> getTransactionsByAccId(@RequestHeader("Authorization") String token,
			@PathVariable("id") long accId) {
		List<Transaction> slist = transRepo.findBySourceAccountIdOrTargetAccountIdOrderByInitiationDate(accId, accId);
		return slist;
	}

	@PostMapping(value = "/withdraw")
	public boolean makeWithdraw(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInput1 accountInput1) {
		transactionService.makeWithdraw(token, accountInput1);
		return true;
	}

	@PostMapping(value = "/servicecharge")
	public boolean makeServiceCharges(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInput1 accountInput1) {
		transactionService.makeServiceCharges(token, accountInput1);
		return true;
	}
	


	@PostMapping(value = "/deposit")
	public ResponseEntity<?> makeDeposit(@RequestHeader("Authorization") String token,
			@Valid @RequestBody AccountInput1 accountInput1) {
		transactionService.makeDeposit(token, accountInput1);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
