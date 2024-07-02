package com.example.exampleservice.controllers;

import com.example.exampleservice.businesslogic.services.ProductService;
import com.example.exampleservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductProxyController {
    private final ProductService productService;

    @Autowired
    public ProductProxyController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductList(){
        return productService.getProductList();
    }

    @KafkaListener(topics = "productDBState", groupId = "dbStateConsumer")
    public void productDBStateListener(List<Product> productList) {
        System.out.println(productList.toString());
    }
}
