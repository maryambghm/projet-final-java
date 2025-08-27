package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.dto.TournamentReceiveDto;
import org.example.projetfinaltournois.dto.TournamentResponseDto;
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


    public TournamentResponseDto create(TournamentReceiveDto tournament) {
        if (tournament.getMaximumPlayer() < 2) throw new IllegalArgumentException("maximum player must be >= 2");
        Tournament created = tournamentRepo.save(tournament.dtoToEntity());
        return created.entityToDto();
    }

    public TournamentResponseDto getById(UUID idTournament) {
        Tournament t = tournamentRepo.findById(idTournament).orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        return t.entityToDto();
    }

    public List<TournamentResponseDto> getAllTournaments() {
        return tournamentRepo.findAll().stream()
                .map(Tournament::entityToDto).toList();
    }

    public Tournament update(UUID idTournament, TournamentReceiveDto tournament) {
        Tournament t = tournamentRepo.findById(idTournament).orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        // MAJ
        Tournament updateT = tournament.dtoToEntity();
        t.setTournamentName(updateT.getTournamentName());
        t.setGameType(updateT.getGameType());
        t.setMatchFormat(updateT.getMatchFormat());
        t.setStartingDate(updateT.getStartingDate());
        t.setEndedDate(updateT.getEndedDate());
        t.setMaximumPlayer(updateT.getMaximumPlayer());
        return tournamentRepo.save(t);
    }

    public void delete(UUID idTournament) {
        tournamentRepo.deleteById(idTournament);
    }

    // Inscriptions
    public void register(UUID idTournament, UUID userId) {
        Tournament t = tournamentRepo.findById(idTournament)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found"));
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        long count = registrationRepo.countByTournament_Id(idTournament);
        if (count >= t.getMaximumPlayer()) throw new IllegalStateException("Tournament full");

        if (registrationRepo.existsByUser_IdUserAndTournament_Id(userId, idTournament)) return;
        registrationRepo.save(Registration.builder().tournament(t).user(u).build());
    }

    public void unregister(UUID tournamentId, UUID userId) {
        registrationRepo.deleteByUser_IdUserAndTournament_Id(userId, tournamentId);
    }

    public List<User> listParticipants(UUID tournamentId) {
        return registrationRepo.findByTournament_Id(tournamentId).stream().map(Registration::getUser).toList();
    }
}

