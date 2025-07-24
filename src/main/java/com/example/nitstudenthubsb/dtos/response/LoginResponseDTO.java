package com.example.nitstudenthubsb.dtos.response;


import com.example.nitstudenthubsb.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private boolean success;

    private String message;

    private String token;

    private UserDTO user;

    public static LoginResponseDTO success(String token, UserDTO user) {
        return new LoginResponseDTO(true, "Login successful", token, user);
    }

    public static LoginResponseDTO failure(String message) {
        return new LoginResponseDTO(false, message, null, null);
    }
}
