package org.example.projetfinaltournois.controller;
import org.example.projetfinaltournois.entity.Match;
import org.example.projetfinaltournois.entity.Player;
import org.example.projetfinaltournois.enums.MatchStatus;
import org.example.projetfinaltournois.service.BracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bracket")
public class BracketController {

    @Autowired
    private BracketService bracketService;

    @PostMapping("/first-round")
    public List<Match> createFirstRound(@RequestBody List<Player> players) {
        return bracketService.createFirstRound(players);
    }

    @PostMapping("/next-round")
    public List<Match> createNextRound(@RequestBody List<Match> previousMatches) {
        return bracketService.createNextRound(previousMatches);
    }

    @PostMapping("/start/{matchId}")
    public Match startMatch(@PathVariable UUID matchId) {
        return bracketService.startMatch(matchId);
    }

    @PostMapping("/finish/{matchId}")
    public Match finishMatch(@PathVariable UUID matchId, @RequestParam UUID winnerId) {
        return bracketService.finishMatch(matchId, winnerId);
    }

    @GetMapping("/current")
    public List<Match> getOngoingMatches() {
        return bracketService.getMatchesByStatus(MatchStatus.ONGOING);
    }

    @GetMapping("/round/{roundNumber}")
    public List<Match> getMatchesByRound(@PathVariable int roundNumber) {
        return bracketService.getMatchesByRound(roundNumber);
    }
}
