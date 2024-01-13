package bolu.ajileye.authfinal.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, ?>> handle( Exception e) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", "error");
        response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error_desc", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("message", e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String, ?>> malformedJwtException( MalformedJwtException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", "error");
        response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error_desc", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("message", e.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
}
