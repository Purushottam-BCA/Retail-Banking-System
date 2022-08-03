package com.rulesservice.service;

import com.rulesservice.model.Account;
import com.rulesservice.model.AuthenticationResponse;
import com.rulesservice.model.RulesInput;

public interface RulesService {

	public boolean evaluate(RulesInput account);

	public AuthenticationResponse hasPermission(String token);

	public double serviceCharges(Account account);

}
