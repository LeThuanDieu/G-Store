package com.example.gstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Document(collection = "categories")
@Data
public class Category {
    @Id
    private String id;
    private String name;
    private String description;
    private boolean deleted = false;   // soft delete
}
