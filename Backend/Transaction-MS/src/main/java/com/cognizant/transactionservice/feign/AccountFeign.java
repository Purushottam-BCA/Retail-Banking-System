package com.cognizant.transactionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.transactionservice.models.Account;

@FeignClient(name = "account-ms", url = "${feign.url-account-service}")
public interface AccountFeign {

	@GetMapping("/getAccount/{accountId}")
	public Account getAccount(@RequestHeader("Authorization") String token,
			@PathVariable(name = "accountId") long accountId);

	@PostMapping("/updateAccount")
	public boolean updateAccount(Account sourceAccount);

	@GetMapping("/updateAccountById/{id}")
	public boolean updateAccountById(@PathVariable("id") long accId, double currentBalance);

}
