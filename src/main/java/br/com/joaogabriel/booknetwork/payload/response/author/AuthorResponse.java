package br.com.joaogabriel.booknetwork.payload.response.author;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthorResponse(
        UUID id,
        String name,
        String email,
        String contact
) implements Serializable {
}
