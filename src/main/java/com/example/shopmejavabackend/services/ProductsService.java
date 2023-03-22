package com.example.shopmejavabackend.services;

import com.example.shopmejavabackend.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductsService {

    RestTemplate restTemplate = new RestTemplate();
    public List<Product> getProducts() throws Exception{
        String PRODUCTS_URL = "https://fakestoreapi.com/products";
        try {
            ResponseEntity<List> response = restTemplate.getForEntity(PRODUCTS_URL, List.class);
            List<Product> products = response.getBody();
            return products;
        }catch (Exception e) {
            throw e;
        }
    }

    public Product getProductById(int id) throws Exception{
        String PRODUCT_URL = "https://fakestoreapi.com/products/"+id;
        try {
            ResponseEntity<Product> response = restTemplate.getForEntity(PRODUCT_URL, Product.class);
            Product product = response.getBody();
            return product;
        } catch (Exception e) {
            throw e;
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> handlingInternalServerError(HttpServerErrorException.InternalServerError ex) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
