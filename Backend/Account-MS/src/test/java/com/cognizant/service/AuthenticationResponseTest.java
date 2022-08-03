package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.cognizant.accountservice.model.AuthenticationResponse;

public class AuthenticationResponseTest {
	
	AuthenticationResponse ae=new AuthenticationResponse();
	AuthenticationResponse ae1=new AuthenticationResponse("234","harini",false);
	@Test
	public void userIdTest()
	{
		ae.setUserid("123");
		assertEquals("123", ae.getUserid());
	}
	@Test
	public void nameTest()
	{
		ae.setName("harini");
		assertEquals("harini", ae.getName());
	}
	@Test
	public void isValidTest()
	{
		ae.setValid(true);
		assertEquals(true, ae.isValid());
	}
	
	@Test
	public void userIdTest1()
	{
		assertEquals("234", ae1.getUserid());
	}
	@Test
	public void nameTest1()
	{
		assertEquals("harini", ae1.getName());
	}
	@Test
	public void isValidTest1()
	{
		assertEquals(false, ae1.isValid());
	}
	
	@Test
	public void toStringTest()
	{
		String expected = ae.toString();
		assertEquals(expected, ae.toString());
	}

}
