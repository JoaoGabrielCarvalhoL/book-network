package br.com.joaogabriel.booknetwork.controller.user.impl;

import br.com.joaogabriel.booknetwork.controller.user.RegisterController;
import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import br.com.joaogabriel.booknetwork.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterControllerImpl implements RegisterController {

    private final UserService userService;

    public RegisterControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<EntityModel<UserCreationResponse>> save(UserPostRequest userPostRequest) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService.save(userPostRequest));
    }
}
