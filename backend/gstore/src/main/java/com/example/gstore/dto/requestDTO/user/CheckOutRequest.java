package com.example.gstore.dto.requestDTO.user;

import lombok.Data;

@Data
public class CheckOutRequest {
    private String cartId;
    private String address;
    private String paymentMethod;
}