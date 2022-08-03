package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.cognizant.accountservice.model.CustomerEntity;


class CustomerEntityTest {

	CustomerEntity customer = new CustomerEntity();

	@Test
	void setUserIdTest() {
		customer.setUserid("1");
		assertEquals("1", customer.getUserid());
	}

	@Test
	void setUserNameTest() {
		customer.setUsername("prabha");
		assertEquals("prabha", customer.getUsername());
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
		customer.setPan("abc");
		assertEquals("abc", customer.getPan());
	}

	@Test
	void getDateTest() {
		Date d = new Date(0);
		customer.setDateOfBirth(d);
		assertEquals(d, customer.getDateOfBirth());
	}
	//CustomerEntity customer2 = new CustomerEntity("111","prabha","prabha",new Date(0),"123","chn");

	CustomerEntity customer2 = new CustomerEntity("111","prabha","prabha",new Date(0),"123","chn");
	@Test
	void setUserIdTest1() {
		assertEquals("111", customer2.getUserid());
	}

	@Test
	void setUserNameTest1() {
		assertEquals("prabha", customer2.getUsername());
	}

	@Test
	void setPasswordTest1() {
		assertEquals("prabha", customer2.getPassword());
	}


	@Test
	void setDateTest1() {
		assertEquals(new Date(0), customer2.getDateOfBirth());
	}
	
	@Test
	void getPanTest1() {
		assertEquals("123", customer2.getPan());
	}
	@Test
	void getAddressTest1() {
		assertEquals("chn", customer2.getAddress());
	}

	


}
