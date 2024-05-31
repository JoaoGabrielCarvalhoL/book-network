package br.com.joaogabriel.booknetwork.payload.response.feedback;

import java.io.Serializable;
import java.util.UUID;

public record FeedbackResponse(
         UUID id, Double rate, String comment
) implements Serializable {
}
