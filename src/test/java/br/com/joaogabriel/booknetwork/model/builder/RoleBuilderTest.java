package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RoleBuilderTest {

    @DisplayName("Given role object, when create a new instance of role of RoleBuilder, then return role object.")
    @Test
    public void givenRoleObject_whenCreateANewInstanceOfRoleBuilder_thenReturnRoleObject() {
        Role built = RoleBuilder.builder()
                .withId(UUID.randomUUID())
                .withName("ROLE_TEST")
                .withDescription("Description of Role Test")
                .build();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(built),
                () -> Assertions.assertInstanceOf(Role.class, built),
                () -> Assertions.assertEquals("ROLE_TEST", built.getName()));
    }
}
