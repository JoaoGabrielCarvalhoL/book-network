package br.com.joaogabriel.booknetwork.handler.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ExceptionResponse(
        String title,
        String message,
        Integer status,
        LocalDateTime timestamp
) implements Serializable {
}
