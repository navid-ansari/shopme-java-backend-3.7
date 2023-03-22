package com.example.shopmejavabackend.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("HealthCheckService Test")
class HealthCheckServiceTest {

    @Test
    @DisplayName("check Healthcheck API")
    void testGetHealthCheck() {
        String expectedResult = "Healthcheck successfull";
        HealthCheckService healthCheckService = new HealthCheckService();
        String actualResult = healthCheckService.getHealthCheck();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}