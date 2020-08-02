package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    public void signup(RegisterRequest registerRequest ){
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);
    }
}
