package org.example.projetfinaltournois.service;

import org.example.projetfinaltournois.dto.RegisterRequestDto;
import org.example.projetfinaltournois.entity.User;
import org.example.projetfinaltournois.exception.UserAlreadyExistException;
import org.example.projetfinaltournois.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User saveUser(RegisterRequestDto registerRequestDto) throws UserAlreadyExistException {
        Optional<User> UserOptional = userRepository.findByEmail(registerRequestDto.getEmail());
        if(UserOptional.isEmpty()){
            User user = new User(registerRequestDto.getEmail(), registerRequestDto.getLastname(), registerRequestDto.getFirstname(), registerRequestDto.getPassword(),registerRequestDto.getAvatar(),0);
            return userRepository.save(user);
        }
        throw new UserAlreadyExistException();
    }
}
