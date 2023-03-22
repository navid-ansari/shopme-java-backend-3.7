package com.example.shopmejavabackend.mocks;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.model.Rating;

public class Product_Mock {

    public Product Product(){

        Product product = new Product();
        product.setId(1);
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setCategory("men's clothing");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        product.setRating(rating);

        return product;
    }
}
