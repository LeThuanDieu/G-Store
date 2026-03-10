package com.example.gstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.gstore.model.CartItem;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByCartId(String cartId);
    // CartItem findByCartIdAndProductId(String cartId, String productId);
    Optional<CartItem> findByCartIdAndProductId(String cartId, String productId);
    void deleteByCartId(String cartId);

    void deleteById(String id);
}