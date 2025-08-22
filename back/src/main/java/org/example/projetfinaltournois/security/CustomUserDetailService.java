package org.example.projetfinaltournois.security;



import org.example.projetfinaltournois.exception.NotFoundException;
import org.example.projetfinaltournois.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<org.example.projetfinaltournois.entity.User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()){
            org.example.projetfinaltournois.entity.User user = userOptional.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("user"));
            return new User(user.getEmail(), user.getPassword(),authorities);
        }
        return null;
    }
}
