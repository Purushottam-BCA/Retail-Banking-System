package com.cognizant.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	private String userid;
	private String name;
	private boolean isValid;
}