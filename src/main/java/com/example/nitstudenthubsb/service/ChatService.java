package com.example.nitstudenthubsb.service;

import com.example.nitstudenthubsb.dtos.ChatMessageDTO;
import com.example.nitstudenthubsb.dtos.ChatResponseDTO;
import com.example.nitstudenthubsb.dtos.OnlineUserDTO;
import com.example.nitstudenthubsb.entity.ChatMessage;
import com.example.nitstudenthubsb.entity.User;
import com.example.nitstudenthubsb.repositories.ChatMessageRepository;
import com.example.nitstudenthubsb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // Store online users in memory (in production, use Redis)
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    public ChatResponseDTO sendMessage(String username, ChatMessageDTO messageDTO) {
        User sender = userRepository.findByRollNo(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatMessage chatMessage = ChatMessage.builder()
                .content(messageDTO.getContent())
                .sender(sender)
                .createdAt(LocalDateTime.now())
                .messageType(ChatMessage.MessageType.TEXT)
                .build();

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // Convert to DTO
        ChatMessageDTO responseDTO = convertToDTO(savedMessage);

        // Broadcast to all connected users via WebSocket
        messagingTemplate.convertAndSend("/topic/chat", responseDTO);

        return ChatResponseDTO.successWithMessage(responseDTO);
    }

    public ChatResponseDTO getChatMessages(PageRequest pageRequest) {
        Page<ChatMessage> messagesPage = chatMessageRepository
                .findAllByOrderByCreatedAtDesc(pageRequest);

        List<ChatMessageDTO> messages = messagesPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ChatResponseDTO.successWithMessages(messages);
    }

    public ChatResponseDTO getOnlineUsers() {
        List<OnlineUserDTO> users = onlineUsers.stream()
                .map(rollNo -> userRepository.findByRollNo(rollNo)
                        .map(this::convertToOnlineUserDTO)
                        .orElse(null))
                .filter(user -> user != null)
                .collect(Collectors.toList());

        return ChatResponseDTO.successWithUsers(users);
    }

    public void addOnlineUser(String rollNo) {
        onlineUsers.add(rollNo);
        broadcastOnlineUsers();
    }

    public void removeOnlineUser(String rollNo) {
        onlineUsers.remove(rollNo);
        broadcastOnlineUsers();
    }

    private void broadcastOnlineUsers() {
        ChatResponseDTO response = getOnlineUsers();
        messagingTemplate.convertAndSend("/topic/online-users", response.getOnlineUsers());
    }

    private ChatMessageDTO convertToDTO(ChatMessage message) {
        return new ChatMessageDTO(
                message.getId(),
                message.getContent(),
                message.getSender().getRollNo(),
                message.getSender().getUsername(),
                message.getSender().getId(),
                message.getCreatedAt(),
                message.getMessageType().name()
        );
    }

    private OnlineUserDTO convertToOnlineUserDTO(User user) {
        return new OnlineUserDTO(
                user.getId(),
                user.getUsername(),
                user.getRollNo(),
                "online",
                user.getUsername().substring(0, 1).toUpperCase()
        );
    }
}
