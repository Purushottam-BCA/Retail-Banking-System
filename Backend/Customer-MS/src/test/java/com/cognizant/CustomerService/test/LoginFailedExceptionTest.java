package com.cognizant.CustomerService.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.cognizant.customerservice.exception.LoginFailedException;

public class LoginFailedExceptionTest {
	
	@Test
	void loginTest()
	{
		LoginFailedException l1=new LoginFailedException("harini");
		LoginFailedException l2=new LoginFailedException("harini");
		assertThat(l1).isNotEqualTo(l2);
	}
	
	@Test
	void loginTest2()
	{
		LoginFailedException l1=new LoginFailedException();
		LoginFailedException l2=new LoginFailedException();
		assertThat(l1).isNotEqualTo(l2);
	}

}
