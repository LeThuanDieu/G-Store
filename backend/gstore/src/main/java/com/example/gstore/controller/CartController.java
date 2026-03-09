package com.example.gstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.user.CartRequest;
import com.example.gstore.model.Cart;
import com.example.gstore.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    public Cart addCart(@RequestBody CartRequest cartRequest){
        return cartService.addCart(cartRequest);
    }
}
