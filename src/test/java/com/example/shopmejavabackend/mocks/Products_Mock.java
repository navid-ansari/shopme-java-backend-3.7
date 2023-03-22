package com.example.shopmejavabackend.mocks;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class Products_Mock {

    public List<Product> products () {

        List<Product> productList = new ArrayList<>();

        // firts product
        Product product1 = new Product();
        product1.setId(1);
        product1.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product1.setPrice(109.95);
        product1.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product1.setCategory("men's clothing");
        product1.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating1 = new Rating();
        rating1.setRate(3.9);
        rating1.setCount(120);

        product1.setRating(rating1);

        productList.add(product1);

        // second product
        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Mens Casual Premium Slim Fit T-Shirts");
        product2.setPrice(22.3);
        product2.setDescription("Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.");
        product2.setCategory("men's clothing");
        product2.setImage("https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg");

        Rating rating2 = new Rating();
        rating2.setRate(4.1);
        rating2.setCount(259);

        product2.setRating(rating1);

        productList.add(product2);

        return productList;

    }
}
