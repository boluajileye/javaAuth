package bolu.ajileye.authfinal.controller.user;

import bolu.ajileye.authfinal.dto.response.UserResponse;
import bolu.ajileye.authfinal.service.UserService;
import bolu.ajileye.authfinal.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<ApiResponse> handle(Principal principal){
        UserResponse response = userService.getUser();
        return ResponseEntity.ok( ).body(
                new ApiResponse(HttpStatus.OK,
                        "User details fetched successfully",
                        Optional.ofNullable(response)));
    }
}
