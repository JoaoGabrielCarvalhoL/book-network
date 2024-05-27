package br.com.joaogabriel.booknetwork.controller.auth.impl;

import br.com.joaogabriel.booknetwork.controller.auth.AuthenticationController;
import br.com.joaogabriel.booknetwork.payload.request.auth.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.auth.JWTTokenResponse;
import br.com.joaogabriel.booknetwork.service.AuthenticationService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<JWTTokenResponse> authenticate(AuthenticateRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authenticationService.authenticate(authRequest));
    }

    @Override
    public ResponseEntity<Void> activate(String token) throws MessagingException {
        this.authenticationService.activateAccount(token);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }
}
