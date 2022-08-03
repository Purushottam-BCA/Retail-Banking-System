package com.rulesservice.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ServiceResponseTest {

	ServiceResponse res = new ServiceResponse();

	@Test
	void setAccountIdTest() {
		res.setAccountId(1);
		assertEquals(1, res.getAccountId());
	}

	@Test
	void setAmountTest() {
		res.setMessage("abcd");
		assertEquals("abcd", res.getMessage());
	}

}
