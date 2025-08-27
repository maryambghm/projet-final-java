package org.example.projetfinaltournois.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TournamentResponseDto {
    private UUID id;
    private String tournamentName;
    private String gameType;
    private String matchFormat;
    private String startingDate;
    private String endedDate;
    private int maximumPlayer;
    private List<UUID> matches;

}
