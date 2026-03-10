package com.example.gstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gstore.dto.requestDTO.user.CartRequest;
import com.example.gstore.model.Cart;
import com.example.gstore.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    public Cart addCart(CartRequest request){
        Cart cart = cartRepository.findByUserId(request.getUserId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(request.getUserId());
            return cartRepository.save(newCart);
              });
              return cart;
    }
    
}
