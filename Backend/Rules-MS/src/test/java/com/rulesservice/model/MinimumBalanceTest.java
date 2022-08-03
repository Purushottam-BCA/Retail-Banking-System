package com.rulesservice.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class MinimumBalanceTest {

	@Test
	public void minBalance() {
		MinimumBalanceException mb = new MinimumBalanceException();
		MinimumBalanceException mb2 = new MinimumBalanceException();
		assertThat(mb).isNotEqualTo(mb2);
	}

	@Test
	public void minBalance2() {
		MinimumBalanceException mb = new MinimumBalanceException("balance error");
		MinimumBalanceException mb2 = new MinimumBalanceException("balance error");
		assertThat(mb).isNotEqualTo(mb2);
	}

}
