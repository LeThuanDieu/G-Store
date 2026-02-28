package com.example.gstore.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "cartItems")
@Data
public class CartItem{
    @Id
    private String id;
    private String cartId;
    private String productId;
    private LocalDateTime createAt = LocalDateTime.now();
    private Integer quantity;
    private boolean deleted = false;   // soft delete
}