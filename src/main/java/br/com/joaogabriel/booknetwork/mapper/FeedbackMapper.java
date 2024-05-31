package br.com.joaogabriel.booknetwork.mapper;

import br.com.joaogabriel.booknetwork.model.entity.Feedback;
import br.com.joaogabriel.booknetwork.payload.request.feedback.FeedbackPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.feedback.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {

    Feedback toFeedback(FeedbackPostRequest feedbackPostRequest);

    FeedbackResponse toFeedbackResponse(Feedback feedback);

    List<FeedbackResponse> toFeedbackResponseList(List<Feedback> feedback);
}
