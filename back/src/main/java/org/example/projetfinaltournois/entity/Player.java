package org.example.projetfinaltournois.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Player {
    @Id
    private UUID id;
    private String username;

    public Player() {
        this.id = UUID.randomUUID();
    }

    public Object getId() {
        return null;
    }
}
