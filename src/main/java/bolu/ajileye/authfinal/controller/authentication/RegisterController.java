package bolu.ajileye.authfinal.controller.authentication;

import bolu.ajileye.authfinal.controller.Controller;
import bolu.ajileye.authfinal.dto.request.RegisterRequest;
import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.service.AuthenticationService;
import bolu.ajileye.authfinal.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class RegisterController{


    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> handle(@RequestBody RegisterRequest registerRequest) {
        UserResponse user = authenticationService.register(registerRequest);;

        return ResponseEntity.ok( ).body(
                ApiResponse.make(HttpStatus.CREATED,
                        "User Registration Success",
                        Optional.ofNullable(user)));
    }
}
