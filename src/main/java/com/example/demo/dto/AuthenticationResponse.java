package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
   // private String refreshToken;
    //private Instant expiresAt;
    private String username;
}
