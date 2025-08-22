package org.example.projetfinaltournois.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SOLO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MatchSolo extends Match{

    @ManyToOne
    private User player1;

    @ManyToOne
    private User player2;

    @ManyToOne
    private User winner;
}
