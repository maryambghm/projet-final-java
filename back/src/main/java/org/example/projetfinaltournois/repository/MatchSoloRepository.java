package org.example.projetfinaltournois.repository;

import org.example.projetfinaltournois.entity.MatchSolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchSoloRepository extends JpaRepository<MatchSolo, UUID> {

    // Récupérer les matchs d'un User
    @Query(""" 
                    SELECT m   FROM MatchSolo m
                    WHERE m.player1.idUser =:userId 
                    OR m.player2.idUser =:userId
            """)
    List<MatchSolo> findByPlayer(@Param("userId") UUID userId);

    // Nombre de victoires d'un User
    long countByWinner_IdUser(UUID userId);

    // Nombre de défaite d'un User
    @Query("""
                           SELECT count(m)  FROM MatchSolo  m
                    WHERE  (m.player1.idUser = :userId OR
            m.player2.idUser= :userId)
                    AND  m.winner IS  NOT NULL
                    AND m.winner.idUser <> :userId
            """)
    long countLossesForUser(@Param("userId") UUID userId);


    // Match SOLO non joués d'un tournoi
    List<MatchSolo> findByTournament_IdAndWinnerIsNull(UUID tournamentId);
}
