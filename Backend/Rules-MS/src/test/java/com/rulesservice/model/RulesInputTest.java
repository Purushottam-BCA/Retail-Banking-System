package com.rulesservice.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rulesservice.service.RulesServiceImpl;

class RulesInputTest {
	RulesInput accInp = new RulesInput();
	RulesInput rul = new RulesInput(1000000003, 100, 10);
	RulesInput rul1 = new RulesInput(1000000004, 1100, 100);
	RulesServiceImpl rs = new RulesServiceImpl();

	@Test
	void EvaluateFalseTest() {

		assertFalse(rs.evaluate(rul));
	}

	@Test
	void EvaluateTrueTest() {

		assertTrue(rs.evaluate(rul1));
	}

	@Test
	void setAccountIdTest() {
		accInp.setAccountId(1000000001);
		assertEquals(1000000001, accInp.getAccountId());
	}

	@Test
	void setAmountTest() {
		accInp.setAmount(500);
		assertEquals(500, accInp.getAmount());
	}

	@Test
	void getAccountIdTest() {
		accInp.setAccountId(1000000003);
		assertTrue(accInp.getAccountId() == rul.getAccountId());
	}

	@Test
	void getCurrBalanceTest() {
		accInp.setCurrentBalance(500);
		assertTrue(accInp.getCurrentBalance() == 500);
	}

	@Test
	void getAmountTest() {
		accInp.setAmount(10);
		assertTrue(accInp.getAmount() == rul.getAmount());
	}

	ServiceResponse res = new ServiceResponse();

	@Test
	void setAccountIdTest1() {
		res.setAccountId(1);
		assertEquals(1, res.getAccountId());
	}

	@Test
	void setAmountTest2() {
		res.setMessage("abcd");
		assertEquals("abcd", res.getMessage());
	}

	@Test
	void setBalanceTest() {
		res.setBalance(2000.0);
		assertEquals(2000.0, res.getBalance());
	}
}
