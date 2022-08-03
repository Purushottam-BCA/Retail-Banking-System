package com.cognizant.transactionservice.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.transactionservice.models.RulesInput;

class RulesTest {

	RulesInput accInp = new RulesInput();
	RulesInput rul = new RulesInput(1, 100, 10);

	@Test
	void setAccountIdTest() {
		accInp.setAccountId(1);
		assertEquals(1, accInp.getAccountId());
	}

	@Test
	void setAmountTest() {
		accInp.setAmount(500);
		assertEquals(500, accInp.getAmount(), 0.0);
	}

	@Test
	void setCurrBalanceTest() {
		accInp.setCurrentBalance(500);
		assertEquals(500, accInp.getCurrentBalance(), 0.0);
	}

	@Test
	void getAccountIdTest() {
		accInp.setAccountId(1);
		assertTrue(accInp.getAccountId() == 1);
	}

	@Test
	void getCurrBalanceTest() {
		accInp.setCurrentBalance(500);
		assertTrue(accInp.getCurrentBalance() == 500);
	}

	@Test
	void getAmountTest() {
		accInp.setAmount(500);
		assertTrue(accInp.getAmount() == 500);
	}

}
