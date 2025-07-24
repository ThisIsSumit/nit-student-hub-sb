package com.example.nitstudenthubsb.controller;

import com.example.nitstudenthubsb.dtos.UserDTO;
import com.example.nitstudenthubsb.dtos.request.LoginRequestDTO;
import com.example.nitstudenthubsb.dtos.request.RegisterRequestDTO;
import com.example.nitstudenthubsb.dtos.response.RegisterResponseDTO;
import com.example.nitstudenthubsb.entity.User;

import com.example.nitstudenthubsb.repositories.UserRepository;
import com.example.nitstudenthubsb.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            RegisterResponseDTO response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            RegisterResponseDTO errorResponse = RegisterResponseDTO.failure(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (RuntimeException e) {
            RegisterResponseDTO errorResponse = RegisterResponseDTO.failure("User with this roll number already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (Exception e) {
            RegisterResponseDTO errorResponse = RegisterResponseDTO.failure("Registration failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}