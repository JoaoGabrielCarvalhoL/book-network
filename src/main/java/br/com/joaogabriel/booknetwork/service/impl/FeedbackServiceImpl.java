package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.mapper.FeedbackMapper;
import br.com.joaogabriel.booknetwork.repository.FeedbackRepository;
import br.com.joaogabriel.booknetwork.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }
}
