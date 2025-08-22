package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.projetfinaltournois.entity.enums.TeamSide;

import java.util.List;

@Entity
@DiscriminatorValue("TEAM")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchTeam extends Match {
    @ManyToMany
    @JoinTable(
            name = "match_teamA_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> teamA;

    @ManyToMany
    @JoinTable(
            name = "match_teamB_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> teamB;


    private TeamSide winnerSide;
}
