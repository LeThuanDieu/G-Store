package com.example.gstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "orderItems")
@Data
public class OrderItem {
    @Id
    private String id;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
}
