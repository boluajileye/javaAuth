package bolu.ajileye.authfinal.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {

    private Long id;
    private String name;

    private Long score;
    private String isGraded;

    public static class CourseResponseBuilder {
        public CourseResponse.CourseResponseBuilder isGraded(Boolean isGraded) {
            this.isGraded = isGraded instanceof Boolean ? isGraded == true ? "true": "false" : "NOTHING";
            return this;
        }
    }
}
