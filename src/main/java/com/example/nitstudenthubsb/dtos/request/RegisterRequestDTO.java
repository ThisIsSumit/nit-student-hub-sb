package com.example.nitstudenthubsb.dtos.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "Roll number is required")
    private String rollNo;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "name can only contain letters, numbers and spaces")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Hostel selection is required")
    private String hostel;

    @NotNull(message = "Languages list cannot be null")
    private List<String> languages;

    @Size(max = 255, message = "Habits description cannot exceed 255 characters")
    private String habits;

    @NotNull(message = "Tech stack list cannot be null")
    private List<String> techStack;

    @Size(max = 500, message = "Other details cannot exceed 500 characters")
    private String otherDetails;
}
