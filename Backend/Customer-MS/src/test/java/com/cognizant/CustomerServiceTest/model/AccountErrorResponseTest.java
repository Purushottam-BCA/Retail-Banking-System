package com.cognizant.CustomerServiceTest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.cognizant.customerservice.model.CustomErrorResponse;



class AccountErrorResponseTest {

	CustomErrorResponse response = new CustomErrorResponse();
	CustomErrorResponse response2 = new CustomErrorResponse(null, HttpStatus.OK, "Not Valid", "Not Created");

	@Test
	void setStatusTest() {
		response.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, response.getStatus());
	}

	@Test
	void setReasonTest() {
		response.setReason("Not Valid");
		assertEquals("Not Valid", response.getReason());
	}

	@Test
	void setMessageTest() {
		response.setMessage("Not Valid");
		assertEquals("Not Valid", response.getMessage());
	}

	@Test
	void setTimeStampTest() {
		response.setTimestamp(null);
		assertEquals(null, response.getTimestamp());
	}
}
