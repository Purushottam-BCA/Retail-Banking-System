package com.cognizant.CustomerServiceTest.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.Transaction;


class TransactionTest {

	Transaction transaction = new Transaction();
	Transaction transaction2 = new Transaction(1l, 1l, "gayathri", 3l, "prabha", 1000, null, "deposit");

	@Test
	void setIdTest() {
		transaction.setId(1);
		assertEquals(1, transaction.getId());
	}

	@Test
	void setSourceAccountIdTest() {
		transaction.setSourceAccountId(1);
		assertEquals(1, transaction.getSourceAccountId());
	}

	@Test
	void setTargetOwnerNameTest() {
		transaction.setTargetOwnerName("gayathri");
		assertEquals("gayathri", transaction.getTargetOwnerName());
	}

	@Test
	void setTargetAccountIdTest() {
		transaction.setTargetAccountId(1);
		;
		assertEquals(1, transaction.getTargetAccountId());
	}

	@Test
	void setAmountTest() {
		transaction.setAmount(1000);
		assertEquals(1000, transaction.getAmount());
	}

	@Test
	void setReferenceTest() {
		transaction.setReference("Deposit");
		assertEquals("Deposit", transaction.getReference());
	}

	@Test
	void setInitiationDateTest() {
		transaction.setInitiationDate(null);
		assertEquals(null, transaction.getInitiationDate());
	}

}
