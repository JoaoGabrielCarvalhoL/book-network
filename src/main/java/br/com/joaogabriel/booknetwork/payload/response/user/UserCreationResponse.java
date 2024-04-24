package br.com.joaogabriel.booknetwork.payload.response.user;

import br.com.joaogabriel.booknetwork.model.entity.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserCreationResponse(
        UUID id,
        String firstname,
        String lastname,
        LocalDate birthOfDate,
        String email,
        List<Role> roles

) implements Serializable {
}
