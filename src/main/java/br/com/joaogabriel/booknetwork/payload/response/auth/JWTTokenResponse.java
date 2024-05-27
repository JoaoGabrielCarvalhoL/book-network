package br.com.joaogabriel.booknetwork.payload.response.auth;

import java.io.Serializable;

public record JWTTokenResponse(
        String token
) implements Serializable {
}
