package bolu.ajileye.authfinal.utils;

import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthHelper {

    private UserRepository userRepository;

    public AuthHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthenticatedUser() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = userRepository.findByEmail(authentication.getName())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toString()) // or set authorities as needed
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<User> userOptional = this.userRepository.findByEmail(userDetails.getUsername());

        return userOptional.orElseThrow(() -> new Exception("No user found"));
    }

}
