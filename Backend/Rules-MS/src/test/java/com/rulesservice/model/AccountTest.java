package com.rulesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountTest {

	Account account = new Account();
	Account account1 = new Account(1,"cust101",6000,"savings",null,"bhavya",null);

	@Test
	void setAccountIdTest() {
		account.setAccountId(1);
		assertEquals(1, account.getAccountId());
	}

	@Test
	void setCustomerIdTest() {
		account.setCustomerId("Cust101");
		assertEquals("Cust101", account.getCustomerId());
	}

	@Test
	void setCurrentBalanceTest() {
		account.setCurrentBalance(5000);
		assertEquals(5000, account.getCurrentBalance());
	}

	@Test
	void setAccountTypeTest() {
		account.setAccountType("Savings");
		assertEquals("Savings", account.getAccountType());
	}

	@Test
	void setOwnerNameTest() {
		account.setOwnerName("bhavya");
		assertEquals("bhavya", account.getOwnerName());
	}

	@Test
	void setTransactionsTest() {
		account.setTransactions(null);
		assertEquals(null, account.getTransactions());
	}
	
	
	@Test
	void setAccountIdTest1() {
		assertEquals(1, account1.getAccountId());
	}

	@Test
	void setCustomerIdTest1() {
		assertEquals("cust101", account1.getCustomerId());
	}

	@Test
	void setCurrentBalanceTest1() {
		assertEquals(6000, account1.getCurrentBalance());
	}

	@Test
	void setAccountTypeTest1() {
		assertEquals("savings", account1.getAccountType());
	}

	@Test
	void setOwnerNameTest1() {
		account.setOwnerName("bhavya");
		assertEquals("bhavya", account1.getOwnerName());
	}

	@Test
	void setTransactionsTest1() {
		assertEquals(null, account1.getTransactions());
	}
}
