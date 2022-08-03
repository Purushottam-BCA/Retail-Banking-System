package com.cognizant.transactionservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.transactionservice.models.TransactionErrorResponse;

public class TransactionErrorResponseTest {
	TransactionErrorResponse transaction = new TransactionErrorResponse();

	@Test
	public void setTimestampTest() {
		transaction.setTimestamp(null);
		assertEquals(null, transaction.getTimestamp());
	}

	@Test
	public void setStatusTest() {
		transaction.setStatus(null);
		assertEquals(null, transaction.getStatus());
	}

	@Test
	public void setMessageTest() {
		transaction.setMessage("anuhya");
		assertEquals("anuhya", transaction.getMessage());
	}

	@Test
	public void setReasonTest() {
		transaction.setReason("anuhya");
		assertEquals("anuhya", transaction.getReason());
	}
}
