package com.rulesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

	AuthenticationResponse response = new AuthenticationResponse();
	AuthenticationResponse response2 = new AuthenticationResponse();

	@Test
	void setUserIdTest() {
		response.setUserid("emp101");
		assertEquals("emp101", response.getUserid());
	}

	@Test
	void setNameTest() {
		response.setName("bhavya");
		assertEquals("bhavya", response.getName());
	}

	@Test
	void setisValidTest() {
		response.setValid(true);
		assertEquals(true, response.isValid());
	}

}