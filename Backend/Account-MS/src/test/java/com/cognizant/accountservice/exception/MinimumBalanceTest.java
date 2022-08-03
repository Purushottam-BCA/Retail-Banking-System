package com.cognizant.accountservice.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cognizant.accountservice.exceptionhandling.MinimumBalanceException;

public class MinimumBalanceTest {
	
	@Test
	public void MinimumException() {
		
		MinimumBalanceException e1=new  MinimumBalanceException("hello");
		MinimumBalanceException e2=new  MinimumBalanceException("hello");
		assertThat(e1).isNotEqualTo(e2);
		
	}
	
	@Test
	public void MinimumExceptionNull() {
		
		MinimumBalanceException e1=new  MinimumBalanceException();
		MinimumBalanceException e2=new  MinimumBalanceException();
		assertThat(e1).isNotEqualTo(e2);
		
	}

}
