package com.cognizant.transactionservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceExceptionTest {
	@InjectMocks
	private MinimumBalanceException minimumBalanceException;

	@Test
	public void testminimumBalance() {
		int reason = 1000;
		assertEquals(reason, 1000);
	}

}
