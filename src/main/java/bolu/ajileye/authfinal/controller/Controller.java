package bolu.ajileye.authfinal.controller;

import bolu.ajileye.authfinal.utils.ApiResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/api")
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome");
    }

    @GetMapping("/**")
    public ResponseEntity<ApiResponse> routeNotFound(HttpRequest request) {
        return ResponseEntity.ok(ApiResponse.make(HttpStatus.BAD_REQUEST, "Route Not Found", Optional.of(request.getURI())));
    }
}
