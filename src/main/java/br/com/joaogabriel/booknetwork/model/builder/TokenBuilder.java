package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.Token;
import br.com.joaogabriel.booknetwork.model.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class TokenBuilder {
    private UUID id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;
    private User user;

    private TokenBuilder() {}

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public TokenBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public TokenBuilder withToken(String token) {
        this.token = token;
        return this;
    }

    public TokenBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TokenBuilder withExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public TokenBuilder withValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
        return this;
    }

    public TokenBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Token build() {
        return new Token(id, token, createdAt, expiresAt, validatedAt, user);
    }
}
