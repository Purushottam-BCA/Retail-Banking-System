package com.cognizant.accountservice.model;

import java.sql.Date;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
	@Getter
	@Setter
	private String userid;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private Date dateOfBirth;
	@Getter
	@Setter
	private String pan;
	@Getter
	@Setter
	private String address;

}