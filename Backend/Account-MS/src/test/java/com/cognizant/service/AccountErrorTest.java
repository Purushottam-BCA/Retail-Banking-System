package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.accountservice.model.AccountErrorResponse;

public class AccountErrorTest {
	
	AccountErrorResponse re=new AccountErrorResponse();
	AccountErrorResponse re1=new AccountErrorResponse(null,null,"invalid login","invalid");

	@Test
	public void dateTest()
	{
		re.setTimestamp(null);
		assertEquals(null, re.getTimestamp());
	}
	@Test
	public void statusTest()
	{
		re.setStatus(null);
		assertEquals(null, re.getStatus());
	}
	@Test
	public void reasonTest()
	{
		re.setReason("invalid login");
		assertEquals("invalid login", re.getReason());
	}
	@Test
	public void messageTest()
	{
		re.setMessage("invalid");
		assertEquals("invalid", re.getMessage());
	}
	
	@Test
	public void dateTest1()
	{
		assertEquals(null, re.getTimestamp());
	}
	@Test
	public void statusTest1()
	{

		assertEquals(null, re1.getStatus());
	}
	@Test
	public void reasonTest1()
	{
		assertEquals("invalid login", re1.getReason());
	}
	@Test
	public void messageTest1()
	{

		assertEquals("invalid", re1.getMessage());
	}
}
