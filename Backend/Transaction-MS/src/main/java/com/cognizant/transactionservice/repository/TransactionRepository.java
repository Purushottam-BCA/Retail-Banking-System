package com.cognizant.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.transactionservice.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findBySourceAccountIdOrTargetAccountIdOrderByInitiationDate(long id, long id2);

	List<Transaction> findByTargetAccountIdOrderByInitiationDate(long accId);

	List<Transaction> findBySourceAccountIdOrderByInitiationDate(int i);
}
