package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.controller.user.RegisterController;
import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.mapper.UserMapper;
import br.com.joaogabriel.booknetwork.model.entity.Role;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import br.com.joaogabriel.booknetwork.repository.RoleRepository;
import br.com.joaogabriel.booknetwork.repository.UserRepository;
import br.com.joaogabriel.booknetwork.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public EntityModel<UserCreationResponse> save(UserPostRequest userPostRequest) {
        log.log(Level.INFO, "Saving user {0} into database", userPostRequest);
        User user = userMapper.toUser(userPostRequest);
        Role role =
                this.roleRepository.findByName("ROLE_USER")
                        .orElseThrow(ResourceNotFoundException::new);
        user.setRoles(List.of(role));
        UserCreationResponse saved
                = userMapper.toUserCreationResponse(this.userRepository.save(user));
        Link link =
                WebMvcLinkBuilder.linkTo(RegisterController.class).slash(saved.id()).withSelfRel();
        EntityModel<UserCreationResponse> entityModel = EntityModel.of(saved);
        entityModel.add(link);
        return entityModel;
    }
}
