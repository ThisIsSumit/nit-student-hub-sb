package com.example.nitstudenthubsb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private String rollNo;
    private String username;
    private String password;
    private String hostel;
    private String languages;
    private String habits;
    private String techStack;
    private String otherDetails;
}




