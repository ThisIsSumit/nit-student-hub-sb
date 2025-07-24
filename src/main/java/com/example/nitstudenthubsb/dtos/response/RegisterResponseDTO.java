package com.example.nitstudenthubsb.dtos.response;

import com.example.nitstudenthubsb.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {

    private boolean success;

    private String message;

    private String token;

    private UserDTO user;

    public static RegisterResponseDTO success(String token, UserDTO user) {
        return new RegisterResponseDTO(true, "Registration successful", token, user);
    }

    public static RegisterResponseDTO failure(String message) {
        return new RegisterResponseDTO(false, message, null, null);
    }
}
