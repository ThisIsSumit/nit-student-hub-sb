package com.example.nitstudenthubsb.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDTO {
    private boolean success;
    private String message;
    private List<ChatMessageDTO> messages;
    private List<OnlineUserDTO> onlineUsers;
    private ChatMessageDTO sentMessage;

    public static ChatResponseDTO success(String message) {
        return new ChatResponseDTO(true, message, null, null, null);
    }

    public static ChatResponseDTO successWithMessages(List<ChatMessageDTO> messages) {
        return new ChatResponseDTO(true, "Messages retrieved successfully", messages, null, null);
    }

    public static ChatResponseDTO successWithUsers(List<OnlineUserDTO> users) {
        return new ChatResponseDTO(true, "Online users retrieved", null, users, null);
    }

    public static ChatResponseDTO successWithMessage(ChatMessageDTO sentMessage) {
        return new ChatResponseDTO(true, "Message sent successfully", null, null, sentMessage);
    }

    public static ChatResponseDTO failure(String message) {
        return new ChatResponseDTO(false, message, null, null, null);
    }
}
