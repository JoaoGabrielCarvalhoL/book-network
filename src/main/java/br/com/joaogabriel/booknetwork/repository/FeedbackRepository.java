package br.com.joaogabriel.booknetwork.repository;

import br.com.joaogabriel.booknetwork.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
