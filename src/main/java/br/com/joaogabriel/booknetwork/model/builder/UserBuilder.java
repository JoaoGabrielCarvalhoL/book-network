package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.Role;
import br.com.joaogabriel.booknetwork.model.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserBuilder {
    private UUID id;
    private String firstname;
    private String lastname;
    private LocalDate birthOfDate;
    private String email;
    private String hashPassword;
    private List<Role> roles;

    private UserBuilder() {}

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder withBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public UserBuilder withRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User build() {
        return new User(id, firstname, lastname, birthOfDate,
                email, hashPassword, roles);
    }
}
