package org.example.projetfinaltournois.controller;

import org.example.projetfinaltournois.dto.LoginRequestDto;
import org.example.projetfinaltournois.dto.LoginResponseDto;
import org.example.projetfinaltournois.dto.RegisterRequestDto;
import org.example.projetfinaltournois.dto.RegisterResponseDto;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.exception.NotFoundException;
import org.example.projetfinaltournois.exception.UserAlreadyExistException;
import org.example.projetfinaltournois.security.JWTGenerator;
import org.example.projetfinaltournois.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public UserController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDTO) throws NotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(LoginResponseDto.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDTO) throws UserAlreadyExistException {
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        User user = userService.saveUser(registerRequestDTO);
        return ResponseEntity.ok(RegisterResponseDto.builder().idUser(user.getIdUser()).email(user.getEmail()).lastname(user.getLastname()).firstname(user.getFirstname()).avatar(user.getAvatar()).role(user.getRole().ordinal()).build());
    }

}
