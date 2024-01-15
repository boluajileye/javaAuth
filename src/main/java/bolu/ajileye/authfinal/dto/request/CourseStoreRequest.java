package bolu.ajileye.authfinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseStoreRequest {

    @NotBlank
    private String name;

    private Long score;
    private Boolean isGraded;
}
