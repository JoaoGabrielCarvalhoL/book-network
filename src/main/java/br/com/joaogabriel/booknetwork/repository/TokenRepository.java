package br.com.joaogabriel.booknetwork.repository;

import br.com.joaogabriel.booknetwork.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByToken(String token);
}
