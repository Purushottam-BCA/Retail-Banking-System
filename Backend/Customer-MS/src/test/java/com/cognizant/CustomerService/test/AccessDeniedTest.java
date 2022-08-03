package com.cognizant.CustomerService.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.cognizant.customerservice.exception.AccessDeniedException;

public class AccessDeniedTest {
	
	//AccessDeniedTest accessDeniedTest=mock(AccessDeniedTest.class);
	@Test
	public void AccessException() {
		
		AccessDeniedException e1=new AccessDeniedException("hello");
		AccessDeniedException e2=new AccessDeniedException("hello");
		assertThat(e1).isNotEqualTo(e2);
		
	}
	
	@Test
	public void AccessExceptionNull() {
		
		AccessDeniedException e1=new AccessDeniedException();
		AccessDeniedException e2=new AccessDeniedException();
		assertThat(e1).isNotEqualTo(e2);
		
	}
	


}
