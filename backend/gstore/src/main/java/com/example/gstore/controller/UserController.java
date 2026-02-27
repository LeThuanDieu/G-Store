package com.example.gstore.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.LoginRequest;
import com.example.gstore.dto.requestDTO.RegisterRequest;
import com.example.gstore.dto.responseDTO.JwtResponse;
import com.example.gstore.dto.responseDTO.RegisterResponse;
import com.example.gstore.model.User;
import com.example.gstore.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        try {
            User saveUser = userService.register(registerRequest);
            return ResponseEntity.ok("Đăng ký  thành công user  :" + saveUser.getUsername());
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        User user = userService.login(loginRequest);
        return ResponseEntity.ok(user);
        
    }
    
}
