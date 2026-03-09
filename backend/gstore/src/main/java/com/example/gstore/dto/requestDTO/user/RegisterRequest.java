    package com.example.gstore.dto.requestDTO.user;

import lombok.Data;

@Data
public class RegisterRequest {
    // username, password, email, roles (ADMIN/USER).
    private String username;
    private String password;
    private String email;
}
