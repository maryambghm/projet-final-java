package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.entity.Registration;
import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.exception.NotFoundException;
import org.example.projetfinaltournois.repository.RegistrationRepository;
import org.example.projetfinaltournois.repository.TournamentRepository;
import org.example.projetfinaltournois.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepo;
    private final RegistrationRepository registrationRepo;
    private final UserRepository userRepo;

    public TournamentService(TournamentRepository tournamentRepo, RegistrationRepository registrationRepo, UserRepository userRepo) {
        this.tournamentRepo = tournamentRepo;
        this.registrationRepo = registrationRepo;
        this.userRepo = userRepo;
    }


    public Tournament create(Tournament tournament) {
        if (tournament.getMaximumPlayer() < 2) throw new IllegalArgumentException("maximum player must be >= 2");
        return tournamentRepo.save(tournament);
    }

    public Tournament getById(UUID idTournament) {
        return tournamentRepo.findById(idTournament).orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepo.findAll();
    }

    public Tournament update(UUID idTournament, Tournament tournament) {
        Tournament t = getById(idTournament);
        t.setTournamentName(tournament.getTournamentName());
        t.setGameType(tournament.getGameType());
        t.setMatchFormat(tournament.getMatchFormat());
        t.setStartingDate(tournament.getStartingDate());
        t.setEndedDate(tournament.getEndedDate());
        t.setMaximumPlayer(tournament.getMaximumPlayer());
        return tournamentRepo.save(t);
    }

    public void delete(UUID idTournament) {
        tournamentRepo.deleteById(idTournament);
    }

    // Inscriptions
    public void register(UUID idTournament, UUID userId) {
        Tournament t = getById(idTournament);
        User u = userRepo.findById(userId).orElseThrow(() -> new NotFoundException());

        long count = registrationRepo.countByTournament_Id(idTournament);
        if (count >= t.getMaximumPlayer()) throw new IllegalStateException("Tournament full");

        if (registrationRepo.existsByUser_IdAndTournament_Id(userId, idTournament)) return;
        registrationRepo.save(Registration.builder().tournament(t).user(u).build());
    }

    public void unregister(UUID tournamentId, UUID userId) {
        registrationRepo.deleteByUser_IDAndTournament_Id(userId, tournamentId);
    }

    public List<User> listParticipants(UUID tournamentId) {
        return registrationRepo.findByTournament_Id(tournamentId).stream().map(Registration::getUser).toList();
    }
}

