package bolu.ajileye.authfinal.event.listener;

import bolu.ajileye.authfinal.event.RegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegisterListener implements ApplicationListener<RegisterEvent> {

    @Override
    public void onApplicationEvent(RegisterEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info(event.toString());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("ran successfully");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info(event.toString());
    }
}
