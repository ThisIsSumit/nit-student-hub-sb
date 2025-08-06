package com.example.nitstudenthubsb.controller;

import com.example.nitstudenthubsb.dtos.ChatMessageDTO;
import com.example.nitstudenthubsb.dtos.ChatResponseDTO;
import com.example.nitstudenthubsb.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<ChatResponseDTO> sendMessage(
            @RequestBody ChatMessageDTO messageDTO,
            Authentication authentication) {

        try {
            String username = authentication.getName();
            ChatResponseDTO response = chatService.sendMessage(username, messageDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ChatResponseDTO.failure("Failed to send message: " + e.getMessage()));
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<ChatResponseDTO> getMessages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            Authentication authentication) {

        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            ChatResponseDTO response = chatService.getChatMessages(pageRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ChatResponseDTO.failure("Failed to load messages: " + e.getMessage()));
        }
    }

    @GetMapping("/online-users")
    public ResponseEntity<ChatResponseDTO> getOnlineUsers(Authentication authentication) {
        try {
            ChatResponseDTO response = chatService.getOnlineUsers();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ChatResponseDTO.failure("Failed to get online users: " + e.getMessage()));
        }
    }
}