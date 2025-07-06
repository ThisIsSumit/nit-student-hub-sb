package com.example.nitstudenthubsb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    private Long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private Group group;
    private String content;
    private String timestamp;
}
