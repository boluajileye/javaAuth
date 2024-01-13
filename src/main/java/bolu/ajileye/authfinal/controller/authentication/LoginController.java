package bolu.ajileye.authfinal.controller.authentication;

import bolu.ajileye.authfinal.dto.request.LoginRequest;
import bolu.ajileye.authfinal.dto.response.AuthenticationResponse;
import bolu.ajileye.authfinal.dto.response.UserResponse;
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
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> main(@RequestBody LoginRequest request) throws Exception {
        AuthenticationResponse user = authenticationService.login(request);
        return ResponseEntity.ok( ).body(
                new ApiResponse(HttpStatus.OK,
                        "User Logged In Successfully",
                        Optional.ofNullable(user)));
    }
}
