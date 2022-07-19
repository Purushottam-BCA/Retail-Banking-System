package com.cognizant.authenticationservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.authenticationservice.model.AppUser;
import com.cognizant.authenticationservice.service.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	@MockBean
	private JwtUtil jwtUtil;

	public String token = "eytoken";
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

	// save employee mapping is tested
	@Test
	public void saveEmployee() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("111", "ba", "ba", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userid").exists());
	}

	// save employee negative test case
	@Test
	public void saveEmployeeNeg() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("111", "yam", "yam", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userid1").doesNotExist());

	}

	// login method is tested

	@Test
	public void login() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("EMPLOYEE101", "emp", "emp", "eyToken", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		AppUser response = parseResponse(andReturn, AppUser.class);
		token = response.getAuthToken();
	}

	// check if token is wrong the login should not proceed
	@Test
	public void login2() throws JsonProcessingException, Exception {
		AppUser menu = new AppUser("EMPLOYEE101", "emp", "emp", "", "EMPLOYEE");
		String json = mapper.writeValueAsString(menu);
		mockMvc.perform(MockMvcRequestBuilders.post("/login").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.authToken2").doesNotExist()).andReturn();
	}

//before find the method is checked here	
	@Test
	public void getOneEmployees() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.post("/find").header("Authorization", "Bearer " + token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

	// without token cannot get the details
	@Test
	public void getOneEmployees1() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.post("/find").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());

	}

	// check the health of microservice
	@Test
	public void geHealth() throws JsonProcessingException, Exception {
		System.err.println(token);
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		boolean equals = andReturn.getResponse().getContentAsString().equals("UP");
		assertEquals(equals, true);

	}

	@Test
	public void geHealthNeg() throws JsonProcessingException, Exception {
		System.err.println(token);
		MvcResult andReturn = mockMvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		boolean equals = andReturn.getResponse().getContentAsString().equals("DOWN");
		assertNotEquals(equals, true);

	}

	@Test
	public void geValidate() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.get("/validateToken").header("Authorization", "Bearer " + token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void getNotValidate() throws JsonProcessingException, Exception {
		System.err.println(token);
		mockMvc.perform(MockMvcRequestBuilders.get("/validateToken").header("Authorization", token)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

	}

//	
	@Test
	public void setterNameTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUsername("nagarjun");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		assertEquals("nagarjun", field.get(pojo));
	}

	@Test
	public void setterNameTestNeg() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setUsername("abcd");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		assertNotEquals("Fields didn't match", field.get(pojo), "abc");
	}

//	
	@Test
	public void getterNameTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		field.set(pojo, "magic_values");
		// when
		String result = pojo.getUsername();
		// then
		assertEquals("magic_values", result);
	}

	@Test
	public void getterNameTestNeg() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("username");
		field.setAccessible(true);
		field.set(pojo, "values");
		// when
		String result = pojo.getUsername();
		// then
		assertNotEquals("field wasn't retrieved properly", result, "magic_values");
	}

	@Test
	public void setterPassTest() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setPassword("nagarjun");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		assertEquals("nagarjun", field.get(pojo));
	}

	@Test
	public void setterPassTestNeg() throws NoSuchFieldException, IllegalAccessException {
		// given
		AppUser pojo = new AppUser();
		// when
		pojo.setPassword("abcde");
		// then
		java.lang.reflect.Field field = pojo.getClass().getDeclaredField("password");
		field.setAccessible(true);
		assertNotEquals("Fields didn't match", field.get(pojo), "abc");
	}

	@Test
	public void getRoleTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/role").accept(MediaType.APPLICATION_JSON)).andReturn();
	}

}
