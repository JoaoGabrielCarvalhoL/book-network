package br.com.joaogabriel.booknetwork.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticateRequest(
        @NotBlank(message = "{empty.field}")
        @Email
        String email,
        @NotBlank(message = "${empty.field}")
        String hashPassword
) implements Serializable {
}
