package br.com.joaogabriel.booknetwork.payload.request.author;

import br.com.joaogabriel.booknetwork.annotation.EmailAvailable;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthorPostRequest(
        @NotBlank(message = "{empty.field}")
        String name,
        @EmailAvailable
        String email,
        @NotBlank(message = "{empty.field}")
        String contact
) implements Serializable {
}
