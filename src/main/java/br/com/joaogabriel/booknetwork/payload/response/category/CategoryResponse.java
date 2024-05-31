package br.com.joaogabriel.booknetwork.payload.response.category;

import java.io.Serializable;
import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String description
) implements Serializable {
}
