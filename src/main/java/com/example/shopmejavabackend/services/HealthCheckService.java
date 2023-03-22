package com.example.shopmejavabackend.services;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public String getHealthCheck() {
        try {
            return "Healthcheck successfull";
        } catch (Exception e) {
            throw e;
        }
    }
}
