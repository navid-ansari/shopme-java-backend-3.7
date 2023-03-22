package com.example.shopmejavabackend.controllers;

import com.example.shopmejavabackend.services.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    @Autowired
    HealthCheckService healthCheckService;

    @GetMapping(value = "/healthcheck")
    public String getHealthCheck(){
        try {
            return this.healthCheckService.getHealthCheck();
        } catch (Exception e) {
            throw e;
        }

    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.InternalServerError ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.BadGateway ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.ServiceUnavailable ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
