package com.example.productdatabaseservice.controllers;

import com.example.productdatabaseservice.entities.Product;
import com.example.productdatabaseservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductRepository productRepository;
    private final KafkaTemplate<Integer, List<Product>> kafkaTemplate;

    @Autowired
    public ProductController(ProductRepository productRepository, KafkaTemplate<Integer, List<Product>> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    //Database state change
    @PostMapping
    public Product addProduct(@RequestBody Product product, @RequestParam(defaultValue = "true") boolean emit) {
        Product addedProduct = productRepository.save(product);
        if(emit) emitProducts(getProductList());
        return product;
    }

    //First connection
    @GetMapping
    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    private void emitProducts(List<Product> productList){
        kafkaTemplate.send("productDBState", productList);
    }
}
