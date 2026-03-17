package com.example.gstore.dto.responseDTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
}
