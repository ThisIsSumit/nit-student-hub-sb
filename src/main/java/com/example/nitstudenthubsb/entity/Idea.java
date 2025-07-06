package com.example.nitstudenthubsb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Idea {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    private String content;
    private int upvotes;
    private String timestamp;
}
