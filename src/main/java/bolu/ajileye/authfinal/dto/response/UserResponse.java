package bolu.ajileye.authfinal.dto.response;

import bolu.ajileye.authfinal.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String about;
    private Role role;
}
