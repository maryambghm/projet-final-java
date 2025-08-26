package org.example.projetfinaltournois.repository;

import org.example.projetfinaltournois.entity.MatchTeam;
import org.example.projetfinaltournois.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchTeamRepository extends JpaRepository<MatchTeam, UUID> {

    // Voir tout les matchs équipes d'un joueur
    @Query("""
            SELECT m 
            FROM MatchTeam m 
            WHERE :user MEMBER OF m.teamA 
            OR  :user MEMBER OF m.teamB
            """)
    List<MatchTeam> findMatchTeamByUser_Id(User user);

    // Victoire TEAM d'un joueur
    @Query("""
                  SELECT COUNT(m) 
                  FROM MatchTeam m
                  WHERE m.winnerSide IS NOT NULL AND (
                  :user MEMBER OF m.teamA AND m.winnerSide = org.example.projetfinaltournois.entity.enums.TeamSide.A
                  OR :user MEMBER OF m.teamB AND m.winnerSide = org.example.projetfinaltournois.entity.enums.TeamSide.B)
            """)
    long countTeamWinsByUser_Id(User user);


    // Afficher défaite TEAM d'un joueur
    @Query("""
            SELECT COUNT(m)
            FROM MatchTeam m
            WHERE m.winnerSide IS NOT NULL AND 
             (:user MEMBER OF m.teamA AND m.winnerSide = org.example.projetfinaltournois.entity.enums.TeamSide.A)
            OR (:user MEMBER OF m.teamB AND m.winnerSide = org.example.projetfinaltournois.entity.enums.TeamSide.B)
            """)
    long countTeamLossesByUser_Id(User user);

    // Match Team d'un tournoi trié par date de début
    List<MatchTeam> findByTournament_Id_OrderByStartingDateAsc(UUID tournamentId);


}
