package com.cognizant.customerservice.controller;

import java.net.BindException;
import java.time.DateTimeException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.customerservice.feign.AuthorizationFeign;
import com.cognizant.customerservice.model.CustomerEntity;
import com.cognizant.customerservice.model.MessageDetails;
import com.cognizant.customerservice.service.CustomerService;

@RestController
@CrossOrigin()
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageDetails messageDetails;
	
	@Autowired
	AuthorizationFeign authorizationFeign;

	/*
	 * Creating a new customer and storing but first checking error on BindingResult
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<?> createCustomer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody CustomerEntity customer, BindingResult bindingResult)
			throws DateTimeException, BindException {
		if (bindingResult.hasErrors()) {
			throw new BindException();
		}
		customerService.hasEmployeePermission(token);
		CustomerEntity customerEntity = customerService.createCustomer(token, customer);
		if (customerEntity != null)
			return new ResponseEntity<>(customerEntity, HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Customer Creation is UNSUCCESSFUL", HttpStatus.NOT_ACCEPTABLE);
	}

	/*
	 * Creating a new customer and storing without checking error on BindingResult
	 */
	@PostMapping("/saveCustomer")
	public CustomerEntity saveCustomer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		CustomerEntity customerEntity = customerService.saveCustomer(token, customer);
		if (customerEntity != null)
			return customerEntity;
		else
			return null;
	}

	/*
	 * Updating existing customer details
	 */
	@PostMapping("/updateCustomer")
	public CustomerEntity updateCustomer(@RequestHeader("Authorization") String token,
			@Valid @RequestBody CustomerEntity customer) {
		customerService.hasEmployeePermission(token);
		return customerService.updateCustomer(token, customer);
	}

	/*
	 * Getting customer details by given customer id
	 */
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token, @PathVariable String id) {
		customerService.hasPermission(token);
		CustomerEntity toReturnCustomerDetails = customerService.getCustomerDetail(token, id);
		if (toReturnCustomerDetails == null)
			return new ResponseEntity<>("Customer Userid " + id + " DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		toReturnCustomerDetails.setPassword(null);
		return new ResponseEntity<>(toReturnCustomerDetails, HttpStatus.OK);
	}
	
	/*
	 * Deleting customer details with given customer id
	 */	
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable String id) {
		customerService.hasPermission(token);
		CustomerEntity toReturnCustomerDetails = customerService.getCustomerDetail(token, id);
		if (toReturnCustomerDetails == null)
			return new ResponseEntity<>("Customer Userid " + id + " DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
		toReturnCustomerDetails.setPassword(null);
		boolean deleteCustomer = customerService.deleteCustomer(id);
		if(deleteCustomer) {
			
			messageDetails.setMessage("CUSTOMER DELETED");
			return new ResponseEntity<>(messageDetails, HttpStatus.OK);			
		}
		return new ResponseEntity<>("Customer Userid " + id + " DOES NOT EXISTS", HttpStatus.NOT_ACCEPTABLE);
	}

	/*
	 * Checking Token is valid or not
	 */	
	@GetMapping("/check")
	public String checkAccessWWithoutValidation(@RequestHeader("Authorization") String token) {
		customerService.hasEmployeePermission(token);
		return "Your Token is valid";
	}

}
