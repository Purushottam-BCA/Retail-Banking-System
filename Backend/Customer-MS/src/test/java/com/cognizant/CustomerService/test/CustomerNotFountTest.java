package com.cognizant.CustomerService.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.exception.CustomerNotFoundException;

public class CustomerNotFountTest {
	

	@Test
	void customerTest()
	{
		CustomerNotFoundException l1=new CustomerNotFoundException("prabha");
		CustomerNotFoundException l2=new CustomerNotFoundException("prabha");
		assertThat(l1).isNotEqualTo(l2);
	}
	
	@Test
	void customerTest2()
	{
		CustomerNotFoundException l1=new CustomerNotFoundException();
		CustomerNotFoundException l2=new CustomerNotFoundException();
		assertThat(l1).isNotEqualTo(l2);
	}
	

}
