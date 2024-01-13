package bolu.ajileye.authfinal.service;

import bolu.ajileye.authfinal.dto.response.UserResponse;

import java.security.Principal;

public interface UserService {
    UserResponse getUser() throws IllegalStateException;
}
