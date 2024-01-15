package bolu.ajileye.authfinal.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class ApiResponse {
    private final String referenceCode = "fretgrbhtnbrevrcretbryytev";
    private final LocalDateTime timestamp = LocalDateTime.now().plusHours(1);
    private String status;
    private int statusCode;
    private String message;
//    private HttpStatus errorCodeValue;
    private Optional<Object> data;

    public ApiResponse(HttpStatus statusCode, String message, Optional<Object> data) {
        this.status = generateStatusfromStatusCode(statusCode.value());
        this.statusCode = statusCode.value();
//        this.errorCodeValue = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatus statusCode, String message) {
        this(statusCode, message, Optional.empty());
    }

    private String generateStatusfromStatusCode(int statusCode) {
        if (statusCode < 300) {
            return "SUCCESS";
        }
            return "ERROR";
    }

    public static ApiResponse make(HttpStatus statusCode, String message, Optional<Object> data){
        return new ApiResponse(statusCode, message, data);
    }
}
