package com.example.nitstudenthubsb.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUserDTO {
    private Long id;
    private String username;
    private String rollNo;
    private String status;
    private String avatar;
}