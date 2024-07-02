package com.example.exampleservice.repositories;

import com.example.exampleservice.models.Product;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Setter
@Repository
public class ProductRepository {
    private RestTemplate restTemplate;

    @Autowired
    public ProductRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> fetchProductList(){
        return restTemplate.getForObject("http://PRODUCTS-DATABASE-SERVICE/products", List.class);
    }
}