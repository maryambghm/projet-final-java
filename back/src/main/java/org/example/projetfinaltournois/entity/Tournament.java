package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projetfinaltournois.dto.TournamentResponseDto;
import org.example.projetfinaltournois.entity.enums.Game;
import org.example.projetfinaltournois.entity.enums.MatchFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue
    @Column(name = "id_tournament")
    private UUID id;

    @Column(nullable = false, name = "tournament_name")
    private String tournamentName;

    // Category jeu
    @Enumerated(EnumType.STRING)
    @Column(name = "game_type")
    private Game gameType;

    // Double ou simple élimination
    @Enumerated(EnumType.STRING)
    @Column(name = "match_format")
    private MatchFormat matchFormat;

    @Column(nullable = false, name = "starting_date")
    private LocalDateTime startingDate;

    @Column(name = "ended_date")
    private LocalDateTime endedDate;


    @Column(nullable = false, name = "max_player")
    private int maximumPlayer;


    // Vue globale des matchs
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matchList;

    public TournamentResponseDto entityToDto() {
        return TournamentResponseDto.builder()
                .id(this.id)
                .tournamentName(this.tournamentName)
                .startingDate(this.startingDate.toString())
                .gameType(this.gameType.toString())
                .endedDate(this.endedDate != null ? this.endedDate.toString() : null)
                .maximumPlayer(this.maximumPlayer)
                .matchFormat(this.matchFormat.name())
                //.matches(this.matchList != null ? this.matchList.stream().map(Match::getIdMatch).toList():new ArrayList<>())
                .matches(new ArrayList<>())
                .build();
    }
}


