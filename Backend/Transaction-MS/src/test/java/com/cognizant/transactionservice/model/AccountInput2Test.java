package com.cognizant.transactionservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.transactionservice.models.AccountInput;


class AccountInput2Test {

	AccountInput accInp = new AccountInput();
	AccountInput accInp1 = new AccountInput(1, 100, 1000);

	@Test
	void setAccountIdTest() {
		accInp.setAccountId(1);
		assertEquals(1, accInp.getAccountId());
	}

	@Test
	void setAmountTest() {
		accInp.setAmount(500);
		assertEquals(500, accInp.getAmount());
	}

	@Test
	void setCurrtentBalTest() {
		accInp.setCurrentBalance(100);
		assertEquals(100, accInp.getCurrentBalance());
	}

	@Test
	void getAccountIdTest() {
		accInp.setAccountId(1);
		assertTrue(accInp.getAccountId() == 1);
	}

	@Test
	void getAmountTest() {
		accInp.setAmount(500);
		assertTrue(accInp.getAmount() == 500);
	}

	@Test
	void getCurrtentBalTest() {
		accInp.setCurrentBalance(500);
		assertTrue(accInp.getCurrentBalance() == 500);
	}
}
