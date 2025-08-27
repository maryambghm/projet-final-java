package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.entity.Match;
import org.example.projetfinaltournois.entity.Player;
import org.example.projetfinaltournois.enums.MatchStatus;
import org.example.projetfinaltournois.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BracketService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> createFirstRound(List<Player> players) {
        List<Match> matches = new ArrayList<>();

        // Génére un match
        for (int i = 0; i < players.size(); i += 2) {
            Match match = new Match();
            match.setRound(1);

            Player player1 = players.get(i);
            match.setPlayer1(player1);

            // Vérification s'il y a un adversaire
            if (i + 1 < players.size()) {
                Player player2 = players.get(i + 1);
                match.setPlayer2(player2);
            } else {
                // S'il n'y a pas d'adversaire alors il continue
                match.setPlayer2(null);
                match.setWinner(player1);
                match.setStatus(MatchStatus.FINISHED);
            }

            matches.add(match);
        }
        matchRepository.saveAll(matches);
        return matches;
    }


    public List<Match> createNextRound(List<Match> previousRoundMatches) {
        List<Player> winners = new ArrayList<>();

        // Récupére les gagnants du round précédent
        for (Match match : previousRoundMatches) {
            if (match.getStatus() != MatchStatus.FINISHED || match.getWinner() == null) {
                throw new IllegalStateException("All matches in the previous round must be finished with a winner before creating the next round.");
            }
            winners.add(match.getWinner());
        }

        // Génére les matchs du round suivant
        List<Match> nextRoundMatches = new ArrayList<>();
        int nextRoundNumber = previousRoundMatches.get(0).getRound() + 1;

        for (int i = 0; i < winners.size(); i += 2) {
            Match match = new Match();
            match.setRound(nextRoundNumber);
            match.setPlayer1(winners.get(i));

            if (i + 1 < winners.size()) {
                match.setPlayer2(winners.get(i + 1));
            } else {
                match.setPlayer2(null);
                match.setWinner(winners.get(i));
                match.setStatus(MatchStatus.FINISHED);
            }

            nextRoundMatches.add(match);
        }
        matchRepository.saveAll(nextRoundMatches);
        return nextRoundMatches;
    }


    public Match startMatch(UUID matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        if (match.getStatus() != MatchStatus.PENDING) {
            throw new IllegalStateException("Match is not in a pending state.");
        }

        match.setStatus(MatchStatus.ONGOING);
        return matchRepository.save(match);
    }

    public Match finishMatch(UUID matchId, UUID winnerId) {
        Match match = matchRepository.findById(matchId)

                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        if (match.getStatus() != MatchStatus.ONGOING) {
            throw new IllegalStateException("Match is not ongoing.");
        }

        if (match.getPlayer1() == null ||
                (match.getPlayer2() == null && !match.getPlayer1().getId().equals(winnerId)) ||
                (match.getPlayer2() != null &&
                        !match.getPlayer1().getId().equals(winnerId) &&
                        !match.getPlayer2().getId().equals(winnerId))) {
            throw new IllegalArgumentException("Winner must be one of the match players.");
        }


        if (match.getPlayer1().getId().equals(winnerId)) {
            match.setWinner(match.getPlayer1());
        } else {
            match.setWinner(match.getPlayer2());
        }

        match.setStatus(MatchStatus.FINISHED);
        return matchRepository.save(match);
    }

    public List<Match> getMatchesByRound(int roundNumber) {
        return matchRepository.findByRound(roundNumber);
    }

    public List<Match> getMatchesByStatus(MatchStatus status) {
        return matchRepository.findByStatus(status);
    }

}

