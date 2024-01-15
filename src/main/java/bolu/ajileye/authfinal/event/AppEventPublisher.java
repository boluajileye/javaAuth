package bolu.ajileye.authfinal.event;

import bolu.ajileye.authfinal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishRegisterEvent(final User user) {
        RegisterEvent registerEvent = new RegisterEvent(this, user);
        applicationEventPublisher.publishEvent(registerEvent);
    }
}
