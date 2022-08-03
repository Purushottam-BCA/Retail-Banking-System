package com.cognizant.authenticationservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.authenticationservice.model.AppUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Tests {

	public String token;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	List<AppUser> employees = new ArrayList<AppUser>();
	static ObjectMapper mapper = new ObjectMapper();

	@Before
	// before testing class login should be done
	// it execute before all methods
	public void setUp() throws JsonProcessingException, Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
		login();
	}

	public static <T> T parseResponse(MvcResult result, Class<T> responseClass)
			throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {

		String contentAsString = result.getResponse().getContentAsString();
		return mapper.readValue(contentAsString, responseClass);

	}

//	@Test
//	public void saveEmployee() throws JsonProcessingException, Exception {
//		AppUser menu = new AppUser("111", "ba", "ba", "", "EMPLOYEE");
//		String json = mapper.writeValueAsString(menu);
//		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.userid").exists());
//	}
//
//	// save employee negative test case
//	@Test
//	public void saveEmployeeNeg() throws JsonProcessingException, Exception {
//		AppUser menu = new AppUser("111", "yam", "yam", "", "EMPLOYEE");
//		String json = mapper.writeValueAsString(menu);
//		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.userid1").doesNotExist());
//
//	}
//
	@Test
	public void login() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("EMPLOYEE101", "emp", "emp", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.post("/login").content(json).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.jsonPath("$.authToken").exists())
				.andReturn();
		AppUser response = parseResponse(andReturn, AppUser.class);
		token = response.getAuthToken();
	}
//
//	// check if token is wrong the login should not proceed
//	@Test
//	public void login2() throws JsonProcessingException, Exception {
//		AppUser menu = new AppUser("EMPLOYEE101", "emp", "emp", "", "EMPLOYEE");
//		String json = mapper.writeValueAsString(menu);
//		MvcResult andReturn = mockMvc
//				.perform(MockMvcRequestBuilders.post("/login").content(json).contentType(MediaType.APPLICATION_JSON)
//						.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.authToken2").doesNotExist())
//				.andReturn();
//	}
//
////before find the method is checked here	
//	@Test
//	public void getOneEmployees() throws JsonProcessingException, Exception {
//		System.err.println(token);
//		mockMvc.perform(MockMvcRequestBuilders.post("/find").header("Authorization", "Bearer " + token)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
//	}

	// without token cannot get the details
	@Test
	public void getOneEmployees1() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.post("/find").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}

}
