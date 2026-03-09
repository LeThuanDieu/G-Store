package com.example.gstore.dto.requestDTO.admin;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    @NotBlank(message = "Không để trống tên sản phẩm !")
    private String name;
    private String description;
    private Double price;
    private List<String> images; // url img
    private String categoryId;
    private Integer stock;
}
