package com.cognizant.customerservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.customerservice.model.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

}
