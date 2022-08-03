package com.cognizant.transactionservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.transactionservice.models.AccountInput1;
import com.cognizant.transactionservice.models.TransactionInput;

class TransactionInputTest {

TransactionInput input = new TransactionInput();
	
	AccountInput1 accIp = new AccountInput1(1, 2000);
	AccountInput1 accIp2 = new AccountInput1(1, 2000);
	TransactionInput input1 = new TransactionInput(accIp,accIp2,3000,"withdraw");
	@Test
	void setSourceAccountTest() {
		input.setSourceAccount(accIp);
		assertEquals(2000, input.getSourceAccount().getAmount());
	}

	@Test
	void setTargetAccountTest() {
		input.setTargetAccount(accIp);
		assertEquals(1, input.getTargetAccount().getAccountId());
	}

	@Test
	void setAmountTest() {
		input.setAmount(1000);
		assertEquals(1000, input.getAmount());
	}

	@Test
	void setReferenceTest() {
		input.setReference("Withdraw");
		assertEquals("Withdraw", input.getReference());
	}
	
	
	@Test
	void setSourceAccountTest1() {
		assertEquals(2000, input1.getSourceAccount().getAmount());
	}

	@Test
	void setTargetAccountTest1() {
		input.setTargetAccount(accIp);
		assertEquals(1, input1.getTargetAccount().getAccountId());
	}

	@Test
	void setAmountTest1() {
		assertEquals(3000, input1.getAmount());
	}

	@Test
	void setReferenceTest1() {
		assertEquals("withdraw", input1.getReference());
	}
}
