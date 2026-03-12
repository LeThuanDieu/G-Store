package com.example.gstore.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Document(collection  = "products")
@Data

public class Product {
    @Id
    private String id ;
    private String name;
    private String description;
    private Double price;
    private List<String> images; // url img
    
    private Integer stock;
    private boolean deleted = false; // soft delete
    private boolean status = true;
    @DocumentReference
    private Category category;
}
