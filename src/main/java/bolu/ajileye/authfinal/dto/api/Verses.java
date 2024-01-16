package bolu.ajileye.authfinal.dto.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Verses {

    private String email;
    private String password;
}
