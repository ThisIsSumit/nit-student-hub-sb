package com.example.nitstudenthubsb.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String rollNo;

    private String username;

    private  String password;

    private String hostel;

    private List<String> languages;

    private String habits;

    private List<String> techStack;

    private String otherDetails;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
