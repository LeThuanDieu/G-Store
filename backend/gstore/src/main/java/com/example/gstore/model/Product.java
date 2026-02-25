package com.example.gstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collation = "products")
@Data

public class Product {
    @Id
    private String id ;
    private String name;
    private Double price;
    private Integer stock;
}
