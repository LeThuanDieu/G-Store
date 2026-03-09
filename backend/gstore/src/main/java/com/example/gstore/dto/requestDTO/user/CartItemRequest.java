package com.example.gstore.dto.requestDTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemRequest {
    private String productId;
    private Integer quantity;
}
