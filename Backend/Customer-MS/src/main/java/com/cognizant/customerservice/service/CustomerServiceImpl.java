package com.cognizant.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.customerservice.exception.AccessDeniedException;
import com.cognizant.customerservice.feign.AccountFeign;
import com.cognizant.customerservice.feign.AuthorizationFeign;
import com.cognizant.customerservice.model.Account;
import com.cognizant.customerservice.model.AppUser;
import com.cognizant.customerservice.model.AuthenticationResponse;
import com.cognizant.customerservice.model.CustomerEntity;
import com.cognizant.customerservice.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final String CUSTOMER = "CUSTOMER";
	
	/**
	 * Autowiring the FeignClient Services to communicate with other microservices
	 */
	@Autowired
	AuthorizationFeign authorizationFeign;
	@Autowired
	AccountFeign accountFeign;

	/**
	 * Autowiring the repository
	 */
	@Autowired
	CustomerRepository customerRepo;

	/**
	 * Validating the token using authorization microservice
	 */
	@Override
	public AuthenticationResponse hasPermission(String token) {
		return authorizationFeign.getValidity(token);
	}

	/**
	 * Checking whether the user has employee permission or not
	 */
	@Override
	public AuthenticationResponse hasEmployeePermission(String token) {
		AuthenticationResponse validity = authorizationFeign.getValidity(token);
		if (!authorizationFeign.getRole(validity.getUserid()).equals("EMPLOYEE"))
			throw new AccessDeniedException("NOT ALLOWED");
		else
			return validity;
	}

	/**
	 * Checking whether the user has customer permission or not
	 */
	@Override
	public AuthenticationResponse hasCustomerPermission(String token) {
		AuthenticationResponse validity = authorizationFeign.getValidity(token);
		if (!authorizationFeign.getRole(validity.getUserid()).equals(CUSTOMER))
			throw new AccessDeniedException("NOT ALLOWED");
		else
			return validity;
	}

	/**
	 * Creating new customer and storing it in the database
	 */
	@Override
	public CustomerEntity createCustomer(String token, CustomerEntity customer) {

		CustomerEntity checkCustomerExists = getCustomerDetail(token, customer.getUserid());
		if (checkCustomerExists == null) {
			AppUser user = new AppUser(customer.getUserid(), customer.getUsername(), customer.getPassword(), null,CUSTOMER);
			authorizationFeign.createUser(user);
		}

		for (Account acc : customer.getAccounts()) {
			accountFeign.createAccount(token, customer.getUserid(), acc);
		}

		customerRepo.save(customer);
		log.info("Customer details saved.");
		return customer;
	}

	/**
	 * Getting the customer details based on the customer id
	 */
	@Override
	public CustomerEntity getCustomerDetail(String token, String id) {
		Optional<CustomerEntity> customer = customerRepo.findById(id);
		if (!customer.isPresent())
			return null;
		log.info("Customer details fetched.");
		List<Account> list = accountFeign.getCustomerAccount(token, id);
		customer.get().setAccounts(list);
		return customer.get();
	}

	/**
	 * Deleting the customer details associated with the given customer id
	 */
	@Override
	public boolean deleteCustomer(String id) {
		CustomerEntity customer = customerRepo.findById(id).get();
		if (customer != null)
			customerRepo.deleteById(id);
		else
			return false;
		log.info("Customer details deleted.");
		return true;
	}
	
	/**
	 * Updating the customer details based on customer id
	 */
	@Override
	public CustomerEntity updateCustomer(String token, CustomerEntity customer) {
		CustomerEntity toUpdate = customerRepo.findById(customer.getUserid()).get();
		toUpdate.setAccounts(customer.getAccounts());
		for (Account acc : customer.getAccounts()) {
			accountFeign.createAccount(token, customer.getUserid(), acc);
		}
		return customerRepo.save(toUpdate);
	}
	
	/**
	 * Saving customer record in the database
	 */
	@Override
	public CustomerEntity saveCustomer(String token, CustomerEntity customer) {
		CustomerEntity checkCustomerExists = getCustomerDetail(token, customer.getUserid());
		if (checkCustomerExists == null) {
			AppUser user = new AppUser(customer.getUserid(), customer.getUsername(), customer.getPassword(), null,	CUSTOMER);
			authorizationFeign.createUser(user);
		}
		log.info("Customer details saved.");
		return customerRepo.save(customer);
	}

}
