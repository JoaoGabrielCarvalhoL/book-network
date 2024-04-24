package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.payload.request.auth.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.auth.JWTTokenResponse;
import br.com.joaogabriel.booknetwork.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    @Override
    public JWTTokenResponse authenticate(AuthenticateRequest authRequest) {
        log.log(Level.INFO, "Authenticate user: {1}", authRequest.email());
        return null;
    }
}
