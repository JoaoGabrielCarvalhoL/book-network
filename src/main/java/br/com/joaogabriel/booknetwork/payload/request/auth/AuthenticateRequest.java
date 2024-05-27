package br.com.joaogabriel.booknetwork.payload.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticateRequest(
        @NotBlank(message = "{empty.field}")
        @Email
        String email,
        @NotBlank(message = "${empty.field}")
        @JsonProperty("password")
        String hashPassword
) implements Serializable {
}
