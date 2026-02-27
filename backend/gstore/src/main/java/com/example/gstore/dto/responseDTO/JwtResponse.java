package com.example.gstore.dto.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String id;
    private String username;
    private String roles;
    private String type="Bearer";   
}
