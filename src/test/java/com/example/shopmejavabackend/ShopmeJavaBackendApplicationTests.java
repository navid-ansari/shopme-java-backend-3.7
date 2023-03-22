package com.example.shopmejavabackend;

import com.example.shopmejavabackend.controllers.HealthCheckController;
import com.example.shopmejavabackend.services.HealthCheckService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("AppicationContext Test")
class ShopmeJavaBackendApplicationTests {

	//private Calculator calculator = new Calculator();

	@Autowired
	private HealthCheckController healthCheckController;

	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Application context")
	void contextLoads() {
		assertThat(healthCheckController).isNotNull();
	}

	/*@Test
	void testSum() {
		int expectedResult = 10;
		int actualResult = calculator.sum(5, 5);
		assertThat(actualResult).isEqualTo(expectedResult);
	}*/

}
