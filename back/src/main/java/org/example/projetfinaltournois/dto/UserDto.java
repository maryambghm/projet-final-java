package org.example.projetfinaltournois.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private UUID idUser;
    private String email;
    private String lastname;
    private String firstname;
    private String avatar;
}
