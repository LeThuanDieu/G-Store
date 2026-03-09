package com.example.gstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gstore.dto.requestDTO.user.CartRequest;
import com.example.gstore.model.Cart;
import com.example.gstore.repository.CartResponsitory;

@Service
public class CartService {
    @Autowired
    CartResponsitory cartResponsitory;
    public Cart addCart(CartRequest request){
        Cart cart = new Cart();
        cart.setUserId(request.getUserId());
        return cartResponsitory.save(cart);
    }
    
}
