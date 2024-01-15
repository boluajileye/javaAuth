package bolu.ajileye.authfinal.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {
    private String id;
    private String name;
    private String brand;
    private String year;

    public static class CarResponseBuilder {
        public  CarResponseBuilder year(String year) {
            this.year = year + " years";
            return this;
        }
    }
}
