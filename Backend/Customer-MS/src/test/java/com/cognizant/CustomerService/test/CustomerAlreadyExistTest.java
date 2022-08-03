package com.cognizant.CustomerService.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cognizant.customerservice.exception.ConsumerAlreadyExistException;

public class CustomerAlreadyExistTest {
	
	
	@Test
	public void customerAlreadyExist() {
		ConsumerAlreadyExistException e1 = new ConsumerAlreadyExistException("hi");
		ConsumerAlreadyExistException e2 = new ConsumerAlreadyExistException("hi");
		assertThat(e1).isNotEqualTo(e2);
	}
	@Test
	public void customerAlreadyExist2() {
		ConsumerAlreadyExistException e1 = new ConsumerAlreadyExistException();
		ConsumerAlreadyExistException e2 = new ConsumerAlreadyExistException();
		assertThat(e1).isNotEqualTo(e2);
	}
	
	@Test
	public void customerAlreadyExist1() {
		ConsumerAlreadyExistException e1 = new ConsumerAlreadyExistException("hello");
		ConsumerAlreadyExistException e2 = new ConsumerAlreadyExistException("hello");
		assertThat(e1).isNotEqualTo(e2);
	}

}
