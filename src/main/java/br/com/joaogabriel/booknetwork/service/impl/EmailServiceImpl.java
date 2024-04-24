package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(String to, String username, String confirmationUrl,
                          String activationCode, String subject) throws MessagingException {
        log.log(Level.INFO, "Sending email to {0}", to);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,
                        StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("development@email.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(generateText(username, confirmationUrl, activationCode), true);

        javaMailSender.send(mimeMessage);
    }

    private String generateText(String username, String confirmationUrl, String activationCode) {
        return STR."""
                <!DOCTYPE html>
                <html>
                <head>
                	<meta charset="utf-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1">
                	<title>Activate Account</title>
                </head>
                <body>
                      <h1>Account Activation</h1>
                      <p>Welcome \{username}</p>
                      <p>Thank's for signing up! Please use the following activation code to activate your account: </p>
                      <span>\{activationCode}</span>
                      <br>
                      <a href="\{confirmationUrl}" target="_blank">Activate your account</a>
                </body>
                </html>
                """;
    }

}
