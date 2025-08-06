package com.example.nitstudenthubsb.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private Long id;
    private String content;
    private String sender;
    private String senderUsername;
    private Long senderId;
    private LocalDateTime timestamp;
    private String type; // 'message', 'system', 'typing'
}