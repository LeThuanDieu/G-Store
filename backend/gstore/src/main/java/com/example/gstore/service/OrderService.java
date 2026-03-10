package com.example.gstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gstore.dto.requestDTO.user.CheckOutRequest;
import com.example.gstore.model.Cart;
import com.example.gstore.model.CartItem;
import com.example.gstore.model.Order;
import com.example.gstore.model.OrderItem;
import com.example.gstore.model.Product;
import com.example.gstore.repository.CartItemRepository;
import com.example.gstore.repository.CartRepository;
import com.example.gstore.repository.OrderRepository;
import com.example.gstore.repository.ProductRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartResponsitory;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Transactional
    public Order creatOrder(String userId){
        Cart cart = cartResponsitory.findByUserId(userId).orElseThrow(()->new RuntimeException("Không tìm thấy giỏ hàng ! "));
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        if(cartItems.isEmpty()){
            throw new RuntimeException("Giỏ hàng trống !");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;
        for(CartItem cartItem: cartItems){
            Product product = productRepository.findById(cartItem.getProductId()).orElseThrow(()->new RuntimeException("Không có sản phẩm  ! "));
            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(product.getPrice());
            orderItems.add(item);
            totalPrice += product.getPrice()*cartItem.getQuantity();
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus("PENDING");

        orderRepository.save(order);

        // clear cart
        cartItemRepository.deleteByCartId(cart.getId());

        return order;
    }

    public List<Order> getOrderHistory(String userId){
        return orderRepository.findByUserIdOrderByCreateAtDesc(userId);
    }
    public Order orderDetail(String orderId){
        return orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Không tìm thấy đơn hàng !"));
    }
    public void deleteOrder(String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Không tìm thấy đơn hàng !"));
        if("PENDING".equals(order.getStatus())){
            orderRepository.delete(order);
        }else {
            throw new RuntimeException("Chỉ có đơn hàng PENDING mới được xóa!");
    }
}
}
