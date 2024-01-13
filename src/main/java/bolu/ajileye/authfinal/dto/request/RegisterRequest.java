package bolu.ajileye.authfinal.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private String about;

    private String phone;

    private String role;
}
