package com.example.gstore.dto.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListResponse {
    private String id;
    private String name;
    private Double price;
    private List<String> images;
}
