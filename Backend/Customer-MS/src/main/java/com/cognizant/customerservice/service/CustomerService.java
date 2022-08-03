package com.cognizant.customerservice.service;

import com.cognizant.customerservice.model.AuthenticationResponse;
import com.cognizant.customerservice.model.CustomerEntity;

public interface CustomerService {

	public CustomerEntity createCustomer(String token, CustomerEntity customer);

	public CustomerEntity getCustomerDetail(String token, String id);
	
	public CustomerEntity saveCustomer(String token, CustomerEntity customer);

	public CustomerEntity updateCustomer(String token, CustomerEntity customer);

	public AuthenticationResponse hasEmployeePermission(String token);

	public boolean deleteCustomer(String id);

	public AuthenticationResponse hasCustomerPermission(String token);

	public AuthenticationResponse hasPermission(String token);

}
