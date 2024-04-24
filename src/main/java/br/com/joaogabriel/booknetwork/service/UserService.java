package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import org.springframework.hateoas.EntityModel;

public interface UserService {

    EntityModel<UserCreationResponse> save(final UserPostRequest userPostRequest);
}
