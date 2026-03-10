package com.example.gstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gstore.dto.requestDTO.user.CartItemRequest;
import com.example.gstore.model.CartItem;
import com.example.gstore.repository.CartItemRepository;


@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductService productService;
    
    @Transactional
    public CartItem addCartItem(CartItemRequest request, String cartId){
        productService.updateStock(request.getProductId(), request.getQuantity());
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, request.getProductId()).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCartId(cartId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
        }
        return cartItemRepository.save(cartItem);
    }
    public CartItem updCartItem(CartItemRequest request, String cartId){
        CartItem cartItem = cartItemRepository.findById(cartId).orElseThrow(()->new RuntimeException("Không có giỏ hàng !"));
        cartItem.setProductId(request.getProductId());
        cartItem.setQuantity(request.getQuantity());
        return cartItemRepository.save(cartItem);
    }
    public void deleteCartItem(String cartId, String productId){
        cartItemRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(()->new RuntimeException("Không tìm thấy !"));;
        // cartItem.setDeleted(true);
        cartItemRepository.deleteByCartId(cartId);
    }
}
