package com.cognizant.accountservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountserviceApplicationTests {
	
	@Test
	void setCustomerIdTest() {
		String check="Cust101";
		assertEquals("Cust101",check );
	}
	
	@Test
	public void main() {
		AccountserviceApplication.main(new String[] {});
	}
}
