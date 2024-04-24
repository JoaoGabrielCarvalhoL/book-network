package br.com.joaogabriel.booknetwork.initialization;

import br.com.joaogabriel.booknetwork.model.entity.Role;
import br.com.joaogabriel.booknetwork.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initialization implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public Initialization(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleRepository.deleteAll();
        this.roleRepository.saveAll(List.of(new Role("ROLE_USER", "User role in the system"),
                new Role("ROLE_ADMIN", "Admin role in the system")));
    }
}
