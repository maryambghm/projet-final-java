package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.repository.RegistrationRepository;
import org.example.projetfinaltournois.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class BracketService {

    private final TournamentRepository tournamentRepo;
    private final RegistrationRepository registrationRepo;

    public BracketService(TournamentRepository tournamentRepo, RegistrationRepository registrationRepo) {
        this.tournamentRepo = tournamentRepo;
        this.registrationRepo = registrationRepo;
    }


}
