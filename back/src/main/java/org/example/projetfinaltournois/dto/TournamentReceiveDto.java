package org.example.projetfinaltournois.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projetfinaltournois.entity.Tournament;
import org.example.projetfinaltournois.entity.enums.Game;
import org.example.projetfinaltournois.entity.enums.MatchFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TournamentReceiveDto {
    private String tournamentName;
    private String gameType;
    private String startingDate;
    private String endedDate;
    private int maximumPlayer;
    private String matchFormat;

    public Tournament dtoToEntity() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return Tournament.builder()
                .tournamentName(this.tournamentName)
                .gameType(Game.valueOf(this.gameType))
                .startingDate(LocalDateTime.parse(this.startingDate, formatter))
                .endedDate(this.endedDate != null ? LocalDateTime.parse(this.endedDate, formatter) : null)
                .maximumPlayer(this.maximumPlayer)
                .matchFormat(MatchFormat.valueOf(this.matchFormat))
                .build();
    }

}