package com.cognizant.accountservice.controller;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.accountservice.feignclient.AuthFeignClient;
import com.cognizant.accountservice.feignclient.TransactionFeign;
import com.cognizant.accountservice.model.Account;
import com.cognizant.accountservice.model.AccountCreationStatus;
import com.cognizant.accountservice.model.AccountInput;
import com.cognizant.accountservice.model.AuthenticationResponse;
import com.cognizant.accountservice.repository.AccountRepository;
import com.cognizant.accountservice.service.AccountServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthFeignClient authFeign;

	@MockBean
	private AccountServiceImpl accountServiceImpl;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private TransactionFeign transactionFeign;
	

	@Test
	void getAccountTest() throws Exception {
		when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("", "", true));
		Account acc = new Account();
		when(accountServiceImpl.getAccount(1)).thenReturn(acc);
		mockMvc.perform(get("/getAccount/1").header("Authorization", "token")).andExpect(status().isOk());
		verify(accountServiceImpl, timeout(1)).getAccount(1);
	}
	

	@Test
	void getCustomerAccountTest() throws Exception {
		when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("", "", true));
		when(accountServiceImpl.getCustomerAccount("token", "cust01")).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/getAccounts/cust01").header("Authorization", "token")).andExpect(status().isOk());
		verify(accountServiceImpl, timeout(1)).getCustomerAccount("token", "cust01");
	}

	@Test
	void createAccountTest() throws Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2021");
		when(accountServiceImpl.hasEmployeePermission("token")).thenReturn(new AuthenticationResponse("emp01", "emp", true));
		Account account = new Account(1, "Cust101", 3000.0, "Savings", date, "Pulkit", null);
		when(accountServiceImpl.createAccount("Cust101", account)).thenReturn(new AccountCreationStatus(1, "Sucessfully Created"));
		mockMvc.perform(MockMvcRequestBuilders
		.post("/createAccount/Cust101")
		.content(asJsonString(account))
		.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		.header("Authorization", "token")).andExpect(status().isNotAcceptable());
		verify(accountServiceImpl, timeout(1)).hasEmployeePermission("token");
	}
	
	@Test
	void checkAccountBalanceTest() throws Exception {
		when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("cust01", "cust", true));
		AccountInput accountIp = new AccountInput();
		Account account = new Account();
		when(accountServiceImpl.getAccount(accountIp.getAccountId())).thenReturn(account);
		mockMvc.perform(MockMvcRequestBuilders.post("/checkBalance")
		.content(asJsonString(accountIp))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.header("Authorization", "token")).andExpect(status().isOk());
		verify(accountServiceImpl, timeout(1)).hasPermission("token");
	}
	
	@Test
	void  getAllAccountTest() throws Exception  {
		when(accountServiceImpl.hasPermission("token")).thenReturn(new AuthenticationResponse("", "", true));
		when(accountServiceImpl.getAllAccounts()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/find").header("Authorization", "token")).andExpect(status().isOk());
		verify(accountServiceImpl, timeout(1)).getAllAccounts();
	}


	public static String asJsonString(final Object obj) throws JsonProcessingException {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;

	}
}
