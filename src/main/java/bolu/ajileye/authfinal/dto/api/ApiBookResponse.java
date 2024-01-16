package bolu.ajileye.authfinal.dto.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiBookResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("iban")
    private String iban;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("routing_number")
    private String routingNumber;

    @JsonProperty("swift_bic")
    private String swiftBic;
}
