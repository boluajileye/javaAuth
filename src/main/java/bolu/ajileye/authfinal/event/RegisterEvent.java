package bolu.ajileye.authfinal.event;

import bolu.ajileye.authfinal.entity.User;
import org.springframework.context.ApplicationEvent;

public class RegisterEvent extends ApplicationEvent {
    private User user;

    public RegisterEvent(Object source, User user){
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
