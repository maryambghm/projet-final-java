package org.example.projetfinaltournois.repository;

import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.enums.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

    List<Tournament> findByGameType(Game gameType);

}
