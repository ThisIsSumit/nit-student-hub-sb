package com.example.nitstudenthubsb.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @NotBlank(message = "Password is required")
    private String password;
}