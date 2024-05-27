package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import jakarta.mail.MessagingException;
import org.springframework.hateoas.EntityModel;

public interface UserService {

    EntityModel<UserCreationResponse> save(final UserPostRequest userPostRequest) throws MessagingException;

    void sendValidationEmail(User user) throws MessagingException;
}
