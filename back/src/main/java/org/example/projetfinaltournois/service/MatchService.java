package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.entity.MatchSolo;
import org.example.projetfinaltournois.entity.MatchTeam;
import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.entity.enums.TeamSide;
import org.example.projetfinaltournois.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {

    private final TournamentRepository tournamentRepo;
    private final UserRepository userRepo;
    private final MatchRepository matchRepo;
    private final MatchSoloRepository matchSoloRepo;
    private final MatchTeamRepository matchTeamRepo;

    public MatchService(TournamentRepository tournamentRepo, UserRepository userRepo, MatchRepository matchRepo, MatchSoloRepository matchSoloRepo, MatchTeamRepository matchTeamRepo) {
        this.tournamentRepo = tournamentRepo;
        this.userRepo = userRepo;
        this.matchRepo = matchRepo;
        this.matchSoloRepo = matchSoloRepo;
        this.matchTeamRepo = matchTeamRepo;
    }

    // SOLO

    // Planifier match SOLO
    public MatchSolo planifiateMatchSolo(UUID tournamentId, UUID player1Id, UUID player2Id, LocalDateTime start, LocalDateTime end, int pointToWin) {
        Tournament t = tournamentRepo.findById(tournamentId).orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        User p1 = userRepo.findById(player1Id).orElseThrow(() -> new IllegalArgumentException("Player1 not found"));
        User p2 = userRepo.findById(player2Id).orElseThrow(() -> new IllegalArgumentException("Player2 not found"));
        MatchSolo m = MatchSolo.builder()
                .tournament(t)
                .matchStartingDate(start)
                .matchEndingDate(end)
                .pointsToWin(pointToWin)
                .player1(p1)
                .player2(p2)
                .winner(null)
                .build();
        return matchSoloRepo.save(m);
    }


    // Saisir résultat SOLO
    public MatchSolo reportSoloResult(UUID matchId, UUID winnerId) {
        MatchSolo m = matchSoloRepo.findById(matchId).orElseThrow(() -> new IllegalArgumentException("Match not found"));
        if (winnerId == null) {
            m.setWinner(null);
            return matchSoloRepo.save(m);
        }
        User w = userRepo.findById(winnerId).orElseThrow(() -> new IllegalArgumentException("Winner not found"));
        if (!(w.getIdUser().equals(m.getPlayer1().getIdUser()) || w.getIdUser().equals(m.getPlayer2().getIdUser()))) {
            throw new IllegalArgumentException("Winner must be one of the players");
        }
        m.setWinner(w);
        return matchSoloRepo.save(m);
    }


    //TEAM
    // Planifier match TEAM
    public MatchTeam planifiateMatchTeam(UUID tournamentId, List<UUID> teamAIds, List<UUID> teamBIds,
                                         LocalDateTime start, LocalDateTime end, int pointToWin)  {
        Tournament t = tournamentRepo.findById(tournamentId).orElseThrow(() -> new IllegalArgumentException("Tournament not found"));;

        List<User> teamA = teamAIds.stream().map(id -> userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id))).toList();

        List<User> teamB = teamBIds.stream().map(id -> userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id))).toList();

        MatchTeam m = MatchTeam.builder()
                .tournament(t)
                .matchStartingDate(start)
                .matchEndingDate(end)
                .pointsToWin(pointToWin)
                .teamA(teamA)
                .teamB(teamB)
                .winnerSide(null)
                .build();
        return matchTeamRepo.save(m);
    }

    // Saisir résultat TEAM
    public MatchTeam reportTeamResult(UUID matchId, TeamSide winnerSide) {
        MatchTeam m = matchTeamRepo.findById(matchId).orElseThrow(() -> new IllegalArgumentException("MatchTeam not found"));
        m.setWinnerSide(winnerSide);
        return matchTeamRepo.save(m);
    }

}
