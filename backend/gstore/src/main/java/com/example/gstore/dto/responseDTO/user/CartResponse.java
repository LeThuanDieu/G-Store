package com.example.gstore.dto.responseDTO.user;

import java.util.List;

import com.example.gstore.dto.requestDTO.user.CartItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String userId;
    private List<CartItemRequest> items;
    private Double totalAmount;
}
