package com.cognizant.transactionservice.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Transient;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private long accountId;

	private String customerId;

	private double currentBalance;

	private String accountType;
	
	private Date openingDate;

	private String ownerName;

	@Transient
	private transient List<Transaction> transactions;

}