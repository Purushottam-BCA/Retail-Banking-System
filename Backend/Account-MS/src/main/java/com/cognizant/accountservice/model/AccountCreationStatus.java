package com.cognizant.accountservice.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class AccountCreationStatus {


	@Id
	@Getter
	@Setter
	private long accountId;
	@Getter
	@Setter
	private String message;


}
