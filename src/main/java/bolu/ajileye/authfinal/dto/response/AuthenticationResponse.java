package bolu.ajileye.authfinal.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticationResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);
}
