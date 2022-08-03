package com.cognizant.CustomerServiceTest.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.AppUser;


class AppUserTest {
	AppUser accInp = new AppUser();
	AppUser accInp2 = new AppUser("2","prabha","prabha","xyz","user");

	@Test
	void setAccountIdTest() {
		accInp.setUserid("1");
		assertEquals("1", accInp.getUserid());
	}

	@Test
	void setRoleTest() {
		accInp.setRole("user");
		assertEquals("user", accInp.getRole());
	}

	@Test
	void setUsernameTest() {
		accInp.setUsername("1");
		assertEquals("1", accInp.getUsername());
	}

	@Test
	void setPassTest() {
		accInp.setPassword("user");
		assertEquals("user", accInp.getPassword());
	}

	@Test
	void setAuthTokenTest() {
		accInp.setAuthToken("user");
		assertEquals("user", accInp.getAuthToken());
	}

	@Test
	void getAccIdTest() {
		accInp.setUserid("1");
		assertEquals("1", accInp.getUserid());
	}

	@Test
	void getRoleTest() {
		accInp.setRole("user");
		assertEquals("user", accInp.getRole());
	}

	@Test
	void getUsernameTest() {
		accInp.setUsername("1");
		assertEquals("1", accInp.getUsername());
	}

	@Test
	void getPasswordTest() {
		accInp.setPassword("user");
		assertEquals("user", accInp.getPassword());
	}

	@Test
	void getTokenTest() {
		accInp.setAuthToken("token");
		assertEquals("token", accInp.getAuthToken());
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
		assertEquals("prabha", accInp2.getUsername());
	}

	@Test
	void getPasswordTest1() {
		assertEquals("prabha", accInp2.getPassword());
	}

	@Test
	void getTokenTest1() {
		assertEquals("xyz", accInp2.getAuthToken());
	}
	
	

}
