package com.example.nitstudenthubsb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Group {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private User creator;
}
