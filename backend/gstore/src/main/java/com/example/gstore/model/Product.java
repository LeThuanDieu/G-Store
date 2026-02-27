package com.example.gstore.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collation = "products")
@Data

public class Product {
    @Id
    private String id ;
    private String name;
    private String description;
    private Double price;
    private List<String> images; // url img
    private String category;
    private Integer stock;
    private boolean deleted = false; // soft delete
}
