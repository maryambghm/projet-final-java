package org.example.projetfinaltournois.repository;

import org.example.projetfinaltournois.entity.Match;
import org.example.projetfinaltournois.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {

    List<Match> findByRound(int round);

    List<Match> findByStatus(MatchStatus status);
}
