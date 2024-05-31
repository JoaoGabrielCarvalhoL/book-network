package br.com.joaogabriel.booknetwork.payload.response.book;

import br.com.joaogabriel.booknetwork.payload.response.author.AuthorResponse;
import br.com.joaogabriel.booknetwork.payload.response.category.CategoryResponse;
import br.com.joaogabriel.booknetwork.payload.response.feedback.FeedbackResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BookResponse(
        UUID id,
        String title,
        @JsonProperty("author")
        AuthorResponse authorResponse,
        @JsonProperty("category")
        CategoryResponse categoryResponse,
        String isbn,
        String synopsis,
        Boolean shareable,
        Boolean archived,
        @JsonProperty("feedback")
        List<FeedbackResponse> feedbackResponse,
        byte[] cover,
        UUID user
) implements Serializable {
}
