package com.example.nitstudenthubsb.repositories;

import com.example.nitstudenthubsb.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Page<ChatMessage> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<ChatMessage> findAllByOrderByCreatedAtAsc(Pageable pageable);
}