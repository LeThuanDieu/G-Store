package com.example.gstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gstore.dto.requestDTO.user.CartItemRequest;
import com.example.gstore.model.CartItem;
import com.example.gstore.repository.CartItemReponsitory;


@Service
public class CartItemService {
    @Autowired
    CartItemReponsitory cartItemReponsitory;
    @Autowired
    ProductService productService;
    
    @Transactional
    public CartItem addCartItem(CartItemRequest request, String cartId){
        productService.updateStock(request.getProductId(), request.getQuantity());
        CartItem cartItem = cartItemReponsitory.findByCartIdAndProductId(cartId, request.getProductId()).orElseThrow(()->new RuntimeException("Khoong tim thay"));
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCartId(cartId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
        }
        return cartItemReponsitory.save(cartItem);
    }
    public CartItem updCartItem(CartItemRequest request, String cartId){
        CartItem cartItem = cartItemReponsitory.findById(cartId).orElseThrow(()->new RuntimeException("Không có giỏ hàng !"));
        cartItem.setProductId(request.getProductId());
        cartItem.setQuantity(request.getQuantity());
        return cartItemReponsitory.save(cartItem);
    }
    public CartItem deleteCartItem(String cartId, String productId){
        CartItem cartItem = cartItemReponsitory.findByCartIdAndProductId(cartId, productId).orElseThrow(()->new RuntimeException("Không tìm thấy !"));;
        cartItem.setDeleted(true);
        return cartItemReponsitory.save(cartItem);
    }
}
