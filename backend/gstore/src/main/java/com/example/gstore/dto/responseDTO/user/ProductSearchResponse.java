package com.example.gstore.dto.responseDTO.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSearchResponse {
    private String id;
    private String name;
    private Double price;
    private List<String> images;
    private String category;
    private Integer stock;

}
