package com.example.gstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.gstore.model.CartItem;

@Repository
public interface CartItemReponsitory extends MongoRepository<CartItem, String> {
    List<CartItem> findByCartIdAndDeletedFalse(String cartId);
    CartItem findByCartIdAndProductIdAndDeletedFalse(String cartId, String productId);
    Optional<CartItem> findByCartIdAndProductId(String cartId, String productId);
}