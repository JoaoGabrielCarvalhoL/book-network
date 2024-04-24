package br.com.joaogabriel.booknetwork.payload.request.user;

import br.com.joaogabriel.booknetwork.annotation.EmailAvailable;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public record UserPostRequest(
        @NotBlank(message = "{empty.field}")
        String firstname,
        @NotBlank(message = "{empty.field}")
        String lastname,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
        LocalDate birthOfDate,
        @EmailAvailable @Email @NotBlank(message = "{empty.field}")
        String email,
        @NotBlank(message = "{empty.field}") @Size(min = 10, message = "Password should be 10 characters minimum.")
        @JsonProperty("password")
        String hashPassword
) implements Serializable {
}
