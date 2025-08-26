package org.example.projetfinaltournois.controller;

import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.service.BracketService;
import org.example.projetfinaltournois.service.TournamentService;
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
        @PostMapping
        public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
            return ResponseEntity.ok(tournamentService.create(tournament));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Tournament> getTournamentById(@PathVariable UUID id) {
            return ResponseEntity.ok(tournamentService.getById(id));
        }

        @GetMapping
        public ResponseEntity<List<Tournament>> getTournaments() {
            return ResponseEntity.ok(tournamentService.getAllTournaments());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Tournament> update(@PathVariable UUID id, @RequestBody Tournament tournament) {
            return ResponseEntity.ok(tournamentService.update(id, tournament));
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