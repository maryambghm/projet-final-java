package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "matchs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "match_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Match {
    @Id
    @GeneratedValue
    @Column(name = "id_match")
    private UUID idMatch;

    @Column(nullable = false, name = "match_starting_date")
    private LocalDateTime matchStartingDate;

    @Column(nullable = false, name = "match_ending_date")
    private LocalDateTime matchEndingDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tournament tournament;

    @Column(nullable = false, name = "point_to_win")
    private int pointsToWin;
}
