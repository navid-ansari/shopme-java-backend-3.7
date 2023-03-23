package com.example.shopmejavabackend.controllers;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.services.HealthCheckService;
import com.example.shopmejavabackend.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    //@CrossOrigin
    @GetMapping(value = "/products")
    public List<Product> getProducts() throws Exception {
        try {
            return this.productsService.getProducts();
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable int id) throws Exception{
        try {
            return this.productsService.getProductById(id);
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
            //throw new Exception(e.getMessage());
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.InternalServerError ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingBadGatewayError(HttpServerErrorException.BadGateway ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingServiceUnavailableError(HttpServerErrorException.ServiceUnavailable ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
