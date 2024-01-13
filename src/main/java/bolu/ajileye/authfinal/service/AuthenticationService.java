package bolu.ajileye.authfinal.service;

import bolu.ajileye.authfinal.dto.request.LoginRequest;
import bolu.ajileye.authfinal.dto.request.RegisterRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;

public interface AuthenticationService {
    UserResponse register(RegisterRequest registerRequest);


    AuthenticationResponse login(LoginRequest request) throws Exception;
}
