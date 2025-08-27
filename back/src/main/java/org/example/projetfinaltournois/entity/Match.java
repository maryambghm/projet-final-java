package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import org.example.projetfinaltournois.enums.MatchStatus;

import java.util.UUID;

@Entity
public class Match {
    @Id
    private UUID id;

    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @ManyToOne
    private Player winner;

    private int round;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    public Match() {
        this.id = UUID.randomUUID();
        this.status = MatchStatus.PENDING; // Par défaut, le match sera en attente
    }

    public void setRound(int i) {
    }

    public void setPlayer1(Player player1) {
    }

    public void setPlayer2(Player player2) {
    }

    public void setWinner(Player player1) {
    }

    public void setStatus(MatchStatus matchStatus) {
    }

    public MatchStatus getStatus() {
        return null;
    }

    public Player getWinner() {
        return null;
    }

    public int getRound() {
        return 0;
    }

    public Player getPlayer1() {
        return null;
    }

    public Player getPlayer2() {
        return null;
    }
}
