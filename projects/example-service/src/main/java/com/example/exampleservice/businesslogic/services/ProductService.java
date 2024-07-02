package com.example.exampleservice.businesslogic.services;

import com.example.exampleservice.models.Product;
import com.example.exampleservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList(){
        return productRepository.fetchProductList();
    }

}