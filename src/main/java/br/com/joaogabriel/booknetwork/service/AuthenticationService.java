package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.payload.request.auth.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.auth.JWTTokenResponse;

public interface AuthenticationService {

    JWTTokenResponse authenticate(AuthenticateRequest authRequest);
}
