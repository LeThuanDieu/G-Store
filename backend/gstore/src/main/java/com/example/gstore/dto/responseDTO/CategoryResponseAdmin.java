package com.example.gstore.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponseAdmin {
    private String id;
    private String name;
    private String description;
    private boolean deleted;
}
