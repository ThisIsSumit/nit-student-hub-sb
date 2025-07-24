package com.example.nitstudenthubsb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
}
