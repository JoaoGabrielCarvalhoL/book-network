package br.com.joaogabriel.booknetwork.model.builder;

import br.com.joaogabriel.booknetwork.model.entity.Role;

import java.util.UUID;

public class RoleBuilder {
    private UUID id;
    private String name;
    private String description;

    private RoleBuilder() {}

    public static RoleBuilder builder(){
        return new RoleBuilder();
    }

    public RoleBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Role build() {
        return new Role(id, name, description);
    }
}
