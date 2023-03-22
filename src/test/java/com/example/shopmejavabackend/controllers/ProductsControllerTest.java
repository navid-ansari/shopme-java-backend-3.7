package com.example.shopmejavabackend.controllers;

import com.example.shopmejavabackend.model.Product;
import com.example.shopmejavabackend.model.Rating;
import com.example.shopmejavabackend.services.ProductsService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ProductsController Test")
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Autowired
    ProductsService productsServiceMock;

    @Test
    @DisplayName("get all Products")
    void getProducts() throws Exception {

        ArrayList<Product> products = new ArrayList<Product>();

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

        Mockito.when(productsServiceMock.getProducts()).thenReturn(products);
        this.mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")))
                .andExpect(jsonPath("$[0].price", is(109.95)))
                .andExpect(jsonPath("$[0].description", is("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")))
                .andExpect(jsonPath("$[0].category", is("men's clothing")))
                .andExpect(jsonPath("$[0].image", is("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")))
                .andExpect(jsonPath("$[0].rating.rate", is(3.9)))
                .andExpect(jsonPath("$[0].rating.count", is(120)));
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("status 200: get product by Id")
    void getProductById() throws Exception {

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

        Mockito.when(productsServiceMock.getProductById(id)).thenReturn(product);

        this.mockMvc.perform(get("/api/v1/products/{id}", id))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.title", is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")))
            .andExpect(jsonPath("$.price", is(109.95)))
            .andExpect(jsonPath("$.description", is("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")))
            .andExpect(jsonPath("$.category", is("men's clothing")))
            .andExpect(jsonPath("$.image", is("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")))
            .andExpect(jsonPath("$.rating.rate", is(3.9)))
            .andExpect(jsonPath("$.rating.count", is(120)));
            //.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("status 400: Bad Request Error")
    void getProductByIdBadRequestError() throws Exception {

        String exceptionParam = "bad_arguments";

        this.mockMvc.perform(get("/api/v1/products/{incorrect_id}", exceptionParam)
         .contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isBadRequest());
         //.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
         //.andExpect(result -> assertEquals("bad_arguments", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("status 404: Not Found Error")
    void getProductByIdNotFoundError() throws Exception {

        String exceptionParam = "not_found";

        this.mockMvc.perform(get("/api/v1/incorrect_url/{id}", exceptionParam)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isNotFound());
        //.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
        //.andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("status 500: Internal Server Error")
    void getProductByIdInternalServerError() throws Exception {

        int id = 1;

        Mockito.when(productsServiceMock.getProductById(id)).thenThrow(HttpServerErrorException.InternalServerError.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.INTERNAL_SERVER_ERROR.value()).isEqualTo(response.getStatus());
        //AssertionsForClassTypes.assertThat("Internal Server Error").isEqualTo(response.getErrorMessage());

        // option 2
        this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("status 502: Bad Gateway Error")
    void getProductByIdBadGatewayError() throws Exception {

        int id = 1;

        Mockito.when(productsServiceMock.getProductById(id)).thenThrow(HttpServerErrorException.BadGateway.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.BAD_GATEWAY.value()).isEqualTo(response.getStatus());
        //AssertionsForClassTypes.assertThat("Internal Server Error").isEqualTo(response.getErrorMessage());

        // option 2
        this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway());
    }

    @Test
    @DisplayName("status 503: Service Unavailable Error")
    void getProductByIdServiceUnavailableError() throws Exception {

        int id = 1;

        Mockito.when(productsServiceMock.getProductById(id)).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        // option 1
        MvcResult result = this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        AssertionsForClassTypes.assertThat(HttpStatus.SERVICE_UNAVAILABLE.value()).isEqualTo(response.getStatus());
        //AssertionsForClassTypes.assertThat("Internal Server Error").isEqualTo(response.getErrorMessage());

        // option 2
        this.mockMvc.perform(get("/api/v1/products/{id}", id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isServiceUnavailable());
    }
}