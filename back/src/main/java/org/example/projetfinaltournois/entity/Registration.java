package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "tournament_id"}))
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

// Table associative inscription users / Tournoi
public class Registration {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Tournament tournament;
}
