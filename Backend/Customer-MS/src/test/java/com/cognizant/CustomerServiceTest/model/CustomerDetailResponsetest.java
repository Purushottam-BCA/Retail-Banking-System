package com.cognizant.CustomerServiceTest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.model.CustomerDetailsResponse;


class CustomerDetailResponsetest {

	CustomerDetailsResponse customer = new CustomerDetailsResponse();
	CustomerDetailsResponse customer2 = new CustomerDetailsResponse("111","bc","bc",new Date(0),"123","chn",null);

	@Test
	void setUserIdTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("sri");
		assertEquals("sri", customer.getUsername());
	}

	@Test
	void setPasswordTest() {
		customer.setPassword("abc");
		assertEquals("abc", customer.getPassword());
	}

	@Test
	void setAddressTest() {
		customer.setAddress("abc");
		assertEquals("abc", customer.getAddress());
	}

	@Test
	void setPanTest() {
		customer.setPan("ABCDE1234R");
		assertEquals("ABCDE1234R", customer.getPan());
	}

	@Test
	void setDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

	@Test
	void getAccTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void getUserNameTest() {
		customer.setUsername("prabha");
		assertEquals("prabha", customer.getUsername());
	}

	@Test
	void getPasswordTest() {
		customer.setPassword("abc");
		assertEquals("abc", customer.getPassword());
	}

	@Test
	void getAddressTest() {
		customer.setAddress("abc");
		assertEquals("abc", customer.getAddress());
	}

	@Test
	void getPanTest() {
		customer.setPan("abcde1234r");
		assertEquals("abcde1234r", customer.getPan());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}

}
