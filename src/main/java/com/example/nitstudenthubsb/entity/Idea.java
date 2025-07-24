package com.example.nitstudenthubsb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    private User user;
    private String content;
    private int upvotes;
    private String timestamp;
}
