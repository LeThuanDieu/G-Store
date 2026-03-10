package com.example.gstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.user.CartItemRequest;
import com.example.gstore.model.CartItem;
import com.example.gstore.service.CartItemService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/cartItems")
public class CartItemController {
    @Autowired
    CartItemService cartItemService ;

    @PostMapping("/{cartId}")
    public CartItem addCartItem(@RequestBody CartItemRequest request, @PathVariable String cartId){
       return cartItemService.addCartItem(request, cartId);
    }
    @PutMapping("/{cartId}")
    public CartItem updCartItem(@RequestBody CartItemRequest request, @PathVariable String cartId){
       return cartItemService.updCartItem(request, cartId);
    }
    @DeleteMapping("/{cartId}/{productId}")
    public void delCartItem(@PathVariable String cartId,@PathVariable String productId){
      cartItemService.deleteCartItem(cartId,productId);
    }
}
