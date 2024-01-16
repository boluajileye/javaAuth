package bolu.ajileye.authfinal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankResponse {


    private String id;


    private String uid;


    private String accountNumber;


    private String iban;


    private String bankName;


    private String routingNumber;


    private String swiftBic;
}
