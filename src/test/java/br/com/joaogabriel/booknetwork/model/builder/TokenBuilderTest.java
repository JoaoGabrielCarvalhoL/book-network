package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class TokenBuilderTest {

    @DisplayName("Given token object, when create a new instance of role of TokenBuilder, then return token object.")
    @Test
    public void givenTokenObject_whenCreateANewInstanceOfTokenBuilder_thenReturnTokenObject() {
        Token built = TokenBuilder.builder()
                .withId(UUID.randomUUID())
                .withToken(String.format("{bcrypt(is a not a token)}%s", UUID.randomUUID()))
                .withCreatedAt(LocalDateTime.now())
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(built),
                () -> Assertions.assertInstanceOf(Token.class, built),
                () -> Assertions.assertNotNull(built.getToken()));
    }
}
