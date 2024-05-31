package br.com.joaogabriel.booknetwork.payload.request.book;

import br.com.joaogabriel.booknetwork.payload.request.author.AuthorPostRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record BookPostRequest(
        @NotBlank(message = "{empty.field}")
        String title,
        @NotBlank(message = "{empty.field}")
        String isbn,
        @NotBlank(message = "{empty.field}")
        String synopsis,
        @NotNull
        Boolean shareable,
        @NotNull
        AuthorPostRequest author
) implements Serializable {
}
