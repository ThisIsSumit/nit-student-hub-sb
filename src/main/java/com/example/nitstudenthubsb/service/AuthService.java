package com.example.nitstudenthubsb.service;

import com.example.nitstudenthubsb.dtos.UserDTO;
import com.example.nitstudenthubsb.dtos.request.LoginRequestDTO;
import com.example.nitstudenthubsb.dtos.request.RegisterRequestDTO;
import com.example.nitstudenthubsb.dtos.response.LoginResponseDTO;
import com.example.nitstudenthubsb.dtos.response.RegisterResponseDTO;
import com.example.nitstudenthubsb.entity.User;
import com.example.nitstudenthubsb.repositories.UserRepository;
import com.example.nitstudenthubsb.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        // Check if user already exists
        if (userRepository.existsByRollNo(request.getRollNo())) {
            throw new RuntimeException("User with this roll number already exists");
        }

        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("User with this username already exists");
        }

        // Create new user
        User user = User.builder()
                .rollNo(request.getRollNo())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .hostel(request.getHostel())
                .languages(request.getLanguages())
                .habits(request.getHabits())
                .techStack(request.getTechStack())
                .otherDetails(request.getOtherDetails())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(savedUser.getRollNo());

        // Convert to DTO
        UserDTO userDTO = convertToUserDTO(savedUser);

        return RegisterResponseDTO.success(token, userDTO);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getRollNo(), request.getPassword())
            );

            // Get user details
            User user = userRepository.findByRollNo(request.getRollNo())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT token
            String token = jwtTokenProvider.generateToken(user.getRollNo());

            // Convert to DTO
            UserDTO userDTO = convertToUserDTO(user);

            return LoginResponseDTO.success(token, userDTO);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .rollNo(user.getRollNo())
                .username(user.getUsername())
                .hostel(user.getHostel())
                .languages(user.getLanguages())
                .habits(user.getHabits())
                .techStack(user.getTechStack())
                .otherDetails(user.getOtherDetails())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}