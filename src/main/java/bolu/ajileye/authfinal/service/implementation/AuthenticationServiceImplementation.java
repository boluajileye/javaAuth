package bolu.ajileye.authfinal.service.implementation;

import bolu.ajileye.authfinal.dto.request.LoginRequest;
import bolu.ajileye.authfinal.dto.request.RegisterRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.enums.Role;
import bolu.ajileye.authfinal.repository.UserRepository;
import bolu.ajileye.authfinal.service.AuthenticationService;
import bolu.ajileye.authfinal.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {


    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        User userData = prepareUserData(registerRequest);

        User user =  userRepository.save(userData);
        return UserResponse.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userRepository.findByEmail(request.getEmail())
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toString()) // or set authorities as needed
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<User> userOptional = this.userRepository.findByEmail(userDetails.getUsername());

        User user = userOptional.orElseThrow(() -> new Exception("No user found"));

        var jwtToken = jwtUtils.generateToken(userDetails);
        var refreshToken = jwtUtils.generateRefreshToken(userDetails);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    private User prepareUserData(RegisterRequest registerRequest){
        return User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .phone(registerRequest.getPhone())
                .password(registerRequest.getPassword())
                .about(registerRequest.getAbout())
                .role(Role.valueOf(registerRequest.getRole()))
                .build();
    }
}
