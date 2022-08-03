package com.rulesservice;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
public class ServletIntializerTest {

	@Mock
	private SpringApplicationBuilder springApplicationBuilder;

	@Test
	public void main() {
		RulesServiceApplication.main(new String[] {});
	}

}
