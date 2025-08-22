package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projetfinaltournois.entity.enums.Game;
import org.example.projetfinaltournois.entity.enums.MatchFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Tournament {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String tournamentName;

    // Category jeu
    private Game gameType;

    // Double ou simple élimination
    private MatchFormat matchFormat;

    @Column(nullable = false)
    private LocalDateTime startingDate;

    private LocalDateTime endedDate;

    @Column(nullable = false)
    private int maximumPlayer;

// Vue globale des matchs
@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Match> matchList;



}

