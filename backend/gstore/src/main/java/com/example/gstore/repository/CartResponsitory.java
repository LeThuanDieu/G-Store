package com.example.gstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gstore.model.Cart;

public interface CartResponsitory extends MongoRepository<Cart, String >{

    
    
}
