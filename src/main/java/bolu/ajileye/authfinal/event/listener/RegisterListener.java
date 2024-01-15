package bolu.ajileye.authfinal.event.listener;

import bolu.ajileye.authfinal.entity.User;
import bolu.ajileye.authfinal.event.RegisterEvent;
import bolu.ajileye.authfinal.service.implementation.EmailService;
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
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        User user = event.getUser();
        String body = "Welcome to my app";
        Context context = new Context();
        context.setVariable("name", user.getFirstName());
        context.setVariable("message", body);
        log.info("listeneer email" + user.getEmail());
        emailService.sendEmail(user.getEmail().toLowerCase(), body, "email-templat", context);

    }
}
