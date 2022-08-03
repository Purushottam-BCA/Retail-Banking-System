package com.cognizant.authenticationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.authenticationservice.errorhandling.ErrorMessage;



public class ErrorMessageTest {
	ErrorMessage errormessage=new ErrorMessage();
	ErrorMessage errormessage1=new ErrorMessage(null,null,"anuhya");
	
	@Test
	public void setTimestampTest() {
		errormessage.setTimestamp(null);
		assertEquals(null, errormessage.getTimestamp());
	}
	@Test
	public void setStatusTest() {
		errormessage.setStatus(null);
		assertEquals(null,errormessage.getStatus());
	}
	@Test
	public void setMessageTest() {
		errormessage.setMessage("anuhya");
		assertEquals("anuhya", errormessage.getMessage());
	}
	@Test
	public void setTimestampTest1() {
		//errormessage.setTimestamp(null);
		assertEquals(null, errormessage1.getTimestamp());
	}
	@Test
	public void setStatusTest1() {
		//errormessage.setStatus(null);
		assertEquals(null,errormessage1.getStatus());
	}
	@Test
	public void setMessageTest1() {
		//errormessage.setMessage("anuhya");
		assertEquals("anuhya", errormessage1.getMessage());
	}
}
