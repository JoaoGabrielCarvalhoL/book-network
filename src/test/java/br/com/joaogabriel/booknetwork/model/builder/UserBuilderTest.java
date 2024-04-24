package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserBuilderTest {

    @DisplayName("Given user object, when create a new instance of user of RoleBuilder, then return user object.")
    @Test
    public void givenUserObject_whenCreateANewInstanceOfUserBuilder_thenReturnUserObject() {
        User built = UserBuilder.builder()
                .withId(UUID.randomUUID())
                .withFirstname("João Gabriel")
                .withLastname("Carvalho")
                .withBirthOfDate(LocalDate.of(1996, 1, 15))
                .withEmail("some@email.com")
                .withHashPassword("BatmanIsTheBruceWayne")
                .withRoles(List.of(RoleBuilder.builder().withId(UUID.randomUUID()).build()))
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(built),
                () -> Assertions.assertInstanceOf(User.class, built),
                () -> Assertions.assertNotNull(built.getRoles()),
                () -> Assertions.assertEquals(built.getRoles().size(), 1),
                () -> Assertions.assertEquals("João Gabriel", built.getFirstname()));
    }
}
