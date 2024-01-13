package bolu.ajileye.authfinal.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}