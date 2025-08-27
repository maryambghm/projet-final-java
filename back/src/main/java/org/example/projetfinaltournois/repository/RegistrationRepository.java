package org.example.projetfinaltournois.repository;

import org.example.projetfinaltournois.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // Vérifier si un User deja inscrit dans un tournoi
    boolean existsByUser_IdUserAndTournament_Id(UUID userId, UUID tournamentId);

    // Compter inscrits (pour vérifier max )
    long countByTournament_Id(UUID tournamentId);

    // Lister inscrits d'un tournoi

    List<Registration> findByTournament_Id(UUID tournamentId);

    // Déscrinscription par admin
    void deleteByUser_IdUserAndTournament_Id(UUID userId, UUID tournamentId);

}
