package bolu.ajileye.authfinal.dto.response;

import bolu.ajileye.authfinal.entity.Car;
import bolu.ajileye.authfinal.entity.Course;
import bolu.ajileye.authfinal.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String about;
    private String verse;
    private Role role;

    private List<CarResponse> car;

    private Set<CourseResponse> courses;
}
