package bolu.ajileye.authfinal.service.implementation;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class EmailService {

    @Value(value = "${spring.mail.host}")
    private String host;

    @Value(value = "${spring.mail.port}")
    private int port;

    @Value(value = "${spring.mail.username}")
    private String username;

    @Value(value = "${spring.mail.password}")
    private String password;

    @Value(value = "${spring.mail.properties.mail.smtp.from}")
    private String from;

    private final TemplateEngine templateEngine;

    public EmailService() {
        log.info("got to mailer");
        this.templateEngine = new TemplateEngine();

    }

    public void sendEmail(String to, String subject, String context) throws MessagingException, UnsupportedEncodingException {
        JavaMailSenderImpl mailSender = getMailSenderInstance();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        log.info("got to mailer func for "  );
        helper.setTo(to);
        helper.setFrom(new InternetAddress(from, "AuthapP"));
        helper.setSubject(subject);
        helper.setText(context, true);
        log.info("got to mailer send");
        mailSender.send(mimeMessage);
    }

    private JavaMailSenderImpl getMailSenderInstance() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        return mailSender;
    }
}
