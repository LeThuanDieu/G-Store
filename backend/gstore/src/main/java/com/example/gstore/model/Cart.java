package com.example.gstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "carts")
@Data
public class Cart {
    @Id
    private String id;
    private String userId;
}
