package com.example.shopmejavabackend.services;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.model.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("ProductsService Test")
class ProductsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductsService productsService;

    @Test
    @DisplayName("status 200 : get all products")
    void getProducts() throws Exception{

        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setId(1);
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setCategory("men's clothing");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        product.setRating(rating);

        products.add(product);

        // mock resttemplate get call
        when(restTemplate.getForEntity("https://fakestoreapi.com/products", List.class))
                .thenReturn(new ResponseEntity(products, HttpStatus.OK));

        // call actual service method
        List<Product> productList = productsService.getProducts();

        // compare returned response
        assertThat(productList, is(products));
    }

    @Test
    @DisplayName("status 200 : get product by Id")
    void getProductById() throws Exception{
        Product product = new Product();
        product.setId(1);
        product.setTitle("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setCategory("men's clothing");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        Rating rating = new Rating();
        rating.setRate(3.9);
        rating.setCount(120);

        product.setRating(rating);

        int id = 1;

        when(restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, Product.class))
               .thenReturn(new ResponseEntity(product, HttpStatus.OK));

        Product fetchProduct = productsService.getProductById(id);

        assertThat(fetchProduct, is(product));

    }

    @Test
    @DisplayName("status 500: Internal Server Error")
    void getProductByIdInternalServerError() throws Exception{
        int id = 1;
        HttpServerErrorException httpServerErrorException = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.getForEntity("https://fakestoreapi.com/products/"+id, Product.class))
                .thenThrow(httpServerErrorException);

        //Product fetchProduct = productsService.getProductById(id);
        //assertThat(fetchProduct, is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}