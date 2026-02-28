package com.example.gstore.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Không để trống tên danh mục !")
    private String name;
    private String description;
}
