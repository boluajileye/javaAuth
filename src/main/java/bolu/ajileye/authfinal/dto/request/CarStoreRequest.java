package bolu.ajileye.authfinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarStoreRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 5, message = "Name must be minimum of 6")
    private String name;

    @NotBlank(message = "Brand cannot be blank")
    @Size(min = 5, message = "Brand must be minimum of 6")
    private String brand;

    @NotBlank(message = "Year cannot be blank")
    @Size(min = 5, message = "Year must be minimum of 6")
    private String year;
}
