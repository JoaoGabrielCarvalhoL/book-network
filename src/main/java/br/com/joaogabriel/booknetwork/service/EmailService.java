package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.model.entity.User;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(String to, String username,
                   String confirmationUrl, String activationCode, String subject) throws MessagingException;


}
