package org.example.projetfinaltournois.controller;

import jakarta.validation.Valid;
import org.example.projetfinaltournois.dto.TournamentReceiveDto;
import org.example.projetfinaltournois.dto.TournamentResponseDto;
import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.service.BracketService;
import org.example.projetfinaltournois.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

    @RestController
    @RequestMapping("/api/tournaments")

    public  class TournamentController {

        private final TournamentService tournamentService;
        private final BracketService bracketService;

        public TournamentController(TournamentService tournamentService, BracketService bracketService) {
            this.tournamentService = tournamentService;
            this.bracketService = bracketService;
        }

        // CRUD
        @PostMapping()
        public ResponseEntity<TournamentResponseDto> createTournament(@RequestBody @Valid TournamentReceiveDto tournament) {
            TournamentResponseDto created = tournamentService.create(tournament);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }

        @GetMapping("/{id}")
        public ResponseEntity<TournamentResponseDto> getTournamentById(@PathVariable UUID id) {
            return ResponseEntity.ok(tournamentService.getById(id));
        }

        @GetMapping()
        public ResponseEntity<List<TournamentResponseDto>> getTournaments() {
            return ResponseEntity.ok(tournamentService.getAllTournaments());
        }

        @PutMapping("/{id}")
        public ResponseEntity<TournamentResponseDto> update(@PathVariable UUID id, @RequestBody TournamentReceiveDto tournament) {
            return ResponseEntity.ok(tournamentService.update(id, tournament).entityToDto());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable UUID id) {
            tournamentService.delete(id);
            return ResponseEntity.ok("Tournament deleted");
        }

        // Inscriptions
        @PostMapping("/{id}/register/{userId}")
        public ResponseEntity<String> registerTournament(@PathVariable UUID id, @PathVariable UUID userId) {
            tournamentService.register(id, userId);
            return ResponseEntity.ok("Tournament registered");
        }

        @DeleteMapping("/{id}/register/{userId}")
        public ResponseEntity<String> deleteTournament(@PathVariable UUID id, @PathVariable UUID userId) {
            tournamentService.unregister(id, userId);
            return ResponseEntity.ok("Tournament deleted");
        }

        @GetMapping("/{id}/register/participants")
        public ResponseEntity<List<User>> participants(@PathVariable UUID id) {
            return ResponseEntity.ok(tournamentService.listParticipants(id));
        }


    }