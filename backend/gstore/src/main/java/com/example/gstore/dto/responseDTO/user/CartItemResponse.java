package com.example.gstore.dto.responseDTO.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private String productId;
    private String name;
    private List<String> images;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
}
