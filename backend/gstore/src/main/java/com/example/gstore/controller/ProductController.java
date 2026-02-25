package com.example.gstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    
}
