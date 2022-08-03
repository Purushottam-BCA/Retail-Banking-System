package com.rulesservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rulesservice.exception.AccessDeniedException;
import com.rulesservice.feign.AuthorizationFeign;
import com.rulesservice.model.AuthenticationResponse;
import com.rulesservice.model.RulesInput;
import com.rulesservice.model.ServiceResponse;

@ExtendWith(SpringExtension.class)
class RulesServiceImplTest {

	@InjectMocks
	RulesServiceImpl serviceImpl;

	@Mock
	AuthorizationFeign authFeignClient;

	@Test
	void EvaluateTest() {
		RulesServiceImpl service = new RulesServiceImpl();
		RulesInput in = new RulesInput(1000, 10000, 10);
		assertEquals(true, service.evaluate(in));
	}

	@Test
	void EvaluateTest2() {
		RulesServiceImpl service = new RulesServiceImpl();
		RulesInput in = new RulesInput(1000, 100, 10);
		assertEquals(false, service.evaluate(in));
	}

	@Test
	void EvaluateTest3() {
		RulesServiceImpl service = new RulesServiceImpl();
		RulesInput in = new RulesInput(1000, 1000, 100);
		assertEquals(false, service.evaluate(in));
	}

	@Test
	void hasPermissionTest1() {
		when(authFeignClient.getValidity("token")).thenReturn(new AuthenticationResponse("EMP101", "emp", true));
		when(authFeignClient.getRole("EMP101")).thenReturn("EMPLOYEE");
		assertTrue(serviceImpl.hasPermission("token").isValid());
	}

	@Test
	void hasPermissionTestFalse() {
		when(authFeignClient.getValidity("token")).thenReturn(new AuthenticationResponse("EMP101", "emp", false));
		when(authFeignClient.getRole("EMP101")).thenReturn("EMPLOYEE");
		assertFalse(serviceImpl.hasPermission("token").isValid());
	}

	@Test
	void hasPermissionTest2() {
		when(authFeignClient.getValidity("token")).thenThrow(new AccessDeniedException("NOT ALLOWED"));
		assertThrows(AccessDeniedException.class, () -> serviceImpl.hasPermission("token"));
	}

}
