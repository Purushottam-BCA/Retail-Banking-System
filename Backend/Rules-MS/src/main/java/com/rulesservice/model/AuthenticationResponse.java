package com.rulesservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class AuthenticationResponse {
	private String userid;
	private String name;
	private boolean isValid;
}