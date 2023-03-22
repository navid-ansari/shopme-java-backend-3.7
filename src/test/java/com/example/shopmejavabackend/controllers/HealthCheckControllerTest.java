package com.example.shopmejavabackend.controllers;

import com.example.shopmejavabackend.services.HealthCheckService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.HttpServerErrorException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("HealthCheckController Test")
class HealthCheckControllerTest {

    @MockBean
    @Autowired
    HealthCheckService healthCheckServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("status 200 : check Healthcheck API")
    void getHealthCheck() throws Exception{
        when(healthCheckServiceMock.getHealthCheck()).thenReturn("Healthcheck successfull");
        this.mockMvc.perform(get("/api/v1/healthcheck")).andExpect(status().is(200))
                .andExpect(content().string("Healthcheck successfull"));
    }

    @Test
    @DisplayName("status 400: Bad Request Error")
    void getHealthCheckBadRequestError() throws Exception {

        String exceptionParam = "bad_arguments";

        this.mockMvc.perform(get("/api/v1/products/healthcheck?invalid_id=200", exceptionParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("status 404: Not Found Error")
    void getHealthCheckNotFoundError() throws Exception{

        String exceptionParam = "not_found";

        this.mockMvc.perform(get("/api/v1/invalid_healthcheck_url", exceptionParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("status 500: Internal Server Error")
    void getHealthCheckInternalServerError() throws Exception {

        int id = 1;

        when(healthCheckServiceMock.getHealthCheck()).thenThrow(HttpServerErrorException.InternalServerError.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.INTERNAL_SERVER_ERROR.value()).isEqualTo(response.getStatus());
        //AssertionsForClassTypes.assertThat("Internal Server Error").isEqualTo(response.getErrorMessage());

        // option 2
        this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().is(500));
    }

    @Test
    @DisplayName("status 502: Bad Gateway Error")
    void getHealthCheckBadGatewayError() throws Exception {

        int id = 1;

        when(healthCheckServiceMock.getHealthCheck()).thenThrow(HttpServerErrorException.BadGateway.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.BAD_GATEWAY.value()).isEqualTo(response.getStatus());

        // option 2
        this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway())
                .andExpect(status().is(502));
    }

    @Test
    @DisplayName("status 503: Service Unavailable Error")
    void getHealthCheckServiceUnavailableError() throws Exception {

        int id = 1;

        when(healthCheckServiceMock.getHealthCheck()).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.SERVICE_UNAVAILABLE.value()).isEqualTo(response.getStatus());
        //AssertionsForClassTypes.assertThat("Internal Server Error").isEqualTo(response.getErrorMessage());

        // option 2
        this.mockMvc.perform(get("/api/v1/healthcheck", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(status().is(503));
    }
}