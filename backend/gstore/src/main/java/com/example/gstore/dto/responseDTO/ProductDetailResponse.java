package com.example.gstore.dto.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ProductDetailResponse {
    private String name;
    private String description;
    private Double price;
    private List<String> images; // url img
    private String categoryId;
    private Integer stock;
}
