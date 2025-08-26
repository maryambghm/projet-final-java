package org.example.projetfinaltournois.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projetfinaltournois.entity.enums.Role;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUser;

    @Column(unique = true)
    private String email;
    private String lastname;
    private String firstname;
    private String password;
    private String avatar;
    private Role role;


    public User(String email, String lastname,String firstname, String password,String avatar, int role){
        this.email=email;
        this.lastname=lastname;
        this.firstname=firstname;
        this.password=password;
        this.avatar = avatar;
        this.role = role == 0 ? Role.PLAYER :Role.ADMIN;
    }




}
