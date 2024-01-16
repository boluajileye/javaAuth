package bolu.ajileye.authfinal.event.listener;

import bolu.ajileye.authfinal.dto.api.ApiBookResponse;
import bolu.ajileye.authfinal.dto.api.BibleResponse;
import bolu.ajileye.authfinal.dto.api.Verses;
import bolu.ajileye.authfinal.entity.Bank;
import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.event.RegisterEvent;
import bolu.ajileye.authfinal.repository.BankRepository;
import bolu.ajileye.authfinal.service.implementation.EmailService;
import bolu.ajileye.authfinal.utils.RestApiUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;


@Component
@Slf4j
@AllArgsConstructor
public class RegisterListener implements ApplicationListener<RegisterEvent> {

    private final EmailService emailService;
    private final RestApiUtil restApiUtil;
    private final BankRepository bankRepository;

    @Override
    public void onApplicationEvent(RegisterEvent event) {
        User user = event.getUser();
        String body = "Welcome to my app";
        Context context = new Context();
        context.setVariable("name", user.getFirstName());
        context.setVariable("message", body);
        log.info("listeneer email" + user.getEmail());
//        emailService.sendEmail(user.getEmail().toLowerCase(), body, "email-template", context);


        log.info("calling bank api");
        ApiBookResponse response = (ApiBookResponse) restApiUtil.get("/banks", ApiBookResponse.class);


        log.info((String) response.toString());
        log.info("adding bank to repo");
        Bank bank = Bank.builder()
                        .uid(response.getUid())
                        .bankName(response.getBankName())
                        .accountNumber(response.getAccountNumber())
                        .iban(response.getIban())
                        .swiftBic(response.getSwiftBic())
                        .routingNumber(response.getRoutingNumber())
                        .user(user)
                        .build();

        bankRepository.save(bank);
        log.info(bank.toString());

        Verses data = Verses.builder().email("bolu@tm30.net").password("Boluwatife12#").build();
        BibleResponse response1 = (BibleResponse) restApiUtil.post("/login", data ,BibleResponse.class);

        log.info(response1.toString());

    }
}
