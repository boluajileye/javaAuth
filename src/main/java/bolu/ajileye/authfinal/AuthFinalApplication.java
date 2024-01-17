package bolu.ajileye.authfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class AuthFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthFinalApplication.class, args);
    }

}
