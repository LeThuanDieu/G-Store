package com.example.gstore.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gstore.model.Product;

public interface ProductRepository extends MongoRepository<Product,String>{  
    
}
