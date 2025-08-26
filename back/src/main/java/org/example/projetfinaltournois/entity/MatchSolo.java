package org.example.projetfinaltournois.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SOLO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "match_solo")
public class MatchSolo extends Match{

    @ManyToOne
    private User player1;

    @ManyToOne
    private User player2;

    @ManyToOne
    private User winner;
}
