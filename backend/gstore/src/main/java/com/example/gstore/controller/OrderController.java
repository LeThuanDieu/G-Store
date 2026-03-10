package com.example.gstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.user.CheckOutRequest;
import com.example.gstore.model.Order;
import com.example.gstore.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/{userId}")
    public Order checkout(@PathVariable String userId){
        return orderService.creatOrder(userId);
    }
    @GetMapping("/history/{userId}")
    public List<Order> histories(@PathVariable String userId){
        return orderService.getOrderHistory(userId);
    }
    @GetMapping("/{orderId}")
    public Order orderDetail(@PathVariable String orderId){
        return orderService.orderDetail(orderId);
    }
    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return "Đã xóa thành công !";
    }
}
