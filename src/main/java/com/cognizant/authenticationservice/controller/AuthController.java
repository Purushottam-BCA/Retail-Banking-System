package com.cognizant.authenticationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationservice.exceptionhandling.AppUserNotFoundException;
import com.cognizant.authenticationservice.model.AppUser;
import com.cognizant.authenticationservice.model.AuthenticationResponse;
import com.cognizant.authenticationservice.repository.UserRepository;
import com.cognizant.authenticationservice.service.CustomerDetailsService;
import com.cognizant.authenticationservice.service.LoginService;
import com.cognizant.authenticationservice.service.Validationservice;

import lombok.extern.slf4j.Slf4j;

/**
 * The AuthController class for request controller
 *
 */
@Slf4j
@RestController
@CrossOrigin()
public class AuthController {

	// Users Repository
	@Autowired
	private UserRepository userRepository;

	// Service class login
	@Autowired
	private LoginService loginService;

	// Service class for login
	@Autowired
	private Validationservice validationService;

	@Autowired
	private CustomerDetailsService customerService;

	/**
	 * The health method to check app
	 *
	 */
	@GetMapping("/health")
	public ResponseEntity<String> healthCheckup() {
		log.info("Health Check for Authentication Microservice");
		log.info("health checkup ----->{}", "up");
		return new ResponseEntity<>("UP", HttpStatus.OK);
	}

	/**
	 * The login method with post request
	 *
	 */

	@PostMapping("/login")
	public ResponseEntity<AppUser> login(@RequestBody AppUser appUserloginCredentials)
			throws UsernameNotFoundException, AppUserNotFoundException {
		AppUser user = loginService.userLogin(appUserloginCredentials);
		log.info("Credentials ----->{}", user);
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

	/**
	 * The token validation method
	 *
	 */
	@GetMapping("/validateToken")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {
		log.info("Token Validation ----->{}", token);
		return validationService.validate(token);
	}

	/**
	 * The user is created with login credentials
	 *
	 */
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody AppUser appUserCredentials) {
		AppUser createduser = null;
		try {
			createduser = userRepository.save(appUserCredentials);
		} catch (Exception e) {
			return new ResponseEntity<String>("Not created", HttpStatus.NOT_ACCEPTABLE);
		}
		log.info("user creation---->{}", createduser);
		return new ResponseEntity<>(createduser, HttpStatus.CREATED);

	}

	/**
	 * The find users method to find all users
	 *
	 */
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	@GetMapping("/find")
	public ResponseEntity<List<AppUser>> findUsers(@RequestHeader("Authorization") final String token) {
		List<AppUser> createduser = new ArrayList<>();
		List<AppUser> findAll = userRepository.findAll();
		findAll.forEach(emp -> createduser.add(emp));
		System.out.println(createduser);
		log.info("All Users  ----->{}", findAll);
		return new ResponseEntity<>(createduser, HttpStatus.CREATED);

	}

	@GetMapping("/role/{id}")
	public String getRole(@PathVariable("id") String id) {
		return userRepository.findById(id).get().getRole();
	}

	@DeleteMapping("deleteCustomer/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String id) {

		System.out.println("Starting deletion of-->" + id);
		customerService.deleteCustomer(id);
		System.out.println("Deleted");
		return new ResponseEntity<>("Deleted SUCCESSFULLY", HttpStatus.OK);
	}

}