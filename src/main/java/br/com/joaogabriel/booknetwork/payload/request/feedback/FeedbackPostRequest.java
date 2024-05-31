package br.com.joaogabriel.booknetwork.payload.request.feedback;

import java.util.UUID;

public record FeedbackPostRequest(Double rate, String comment) {
}
