package com.rulesservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppUserTest {

	AppUser accInp = new AppUser();
	AppUser accInp2 = new AppUser("2","bhavya","bhavya","xyz","user");

	@Test
	void setAccountIdTest() {
		accInp.setUsername("bhavya");
		assertEquals("bhavya", accInp.getUsername());
	}

	@Test
	void setAmountTest() {
		accInp.setUserid("emp");
		assertEquals("emp", accInp.getUserid());
	}

	@Test
	public void setPasswoedTest() {
		accInp.setPassword("abc");
		assertEquals("abc", accInp.getPassword());
	}

	@Test
	public void setAuthTokenTest() {
		accInp.setAuthToken("token");
		assertEquals("token", accInp.getAuthToken());
	}

	@Test
	public void setRoleTest() {
		accInp.setRole("user");
		assertEquals("user", accInp.getRole());
	}
	
	@Test
	void getAccIdTest1() {
		assertEquals("2", accInp2.getUserid());
	}

	@Test
	void getRoleTest1() {
		assertEquals("user", accInp2.getRole());
	}

	@Test
	void getUsernameTest1() {
		assertEquals("bhavya", accInp2.getUsername());
	}

	@Test
	void getPasswordTest1() {
		assertEquals("bhavya", accInp2.getPassword());
	}

	@Test
	void getTokenTest1() {
		assertEquals("xyz", accInp2.getAuthToken());
	}
}