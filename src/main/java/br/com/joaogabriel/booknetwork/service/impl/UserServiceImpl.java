package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.controller.user.RegisterController;
import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.mapper.UserMapper;
import br.com.joaogabriel.booknetwork.model.builder.TokenBuilder;
import br.com.joaogabriel.booknetwork.model.entity.Role;
import br.com.joaogabriel.booknetwork.model.entity.Token;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import br.com.joaogabriel.booknetwork.repository.RoleRepository;
import br.com.joaogabriel.booknetwork.repository.TokenRepository;
import br.com.joaogabriel.booknetwork.repository.UserRepository;
import br.com.joaogabriel.booknetwork.service.EmailService;
import br.com.joaogabriel.booknetwork.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final String confirmationUrl;
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           TokenRepository tokenRepository,
                           EmailService emailService,
                           @Value("${mailing.frontend.activation-url}") String confirmationUrl) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.confirmationUrl = confirmationUrl;
    }

    @Override
    public EntityModel<UserCreationResponse> save(UserPostRequest userPostRequest) throws MessagingException {
        User user = hash(userPostRequest);
        log.log(Level.INFO, "Saving user {0} into database", user);
        Role role =
                this.roleRepository.findByName("ROLE_USER")
                        .orElseThrow(ResourceNotFoundException::new);
        user.setRoles(List.of(role));
        UserCreationResponse saved
                = userMapper.toUserCreationResponse(this.userRepository.save(user));
        sendValidationEmail(user);
        Link link =
                WebMvcLinkBuilder.linkTo(RegisterController.class).slash(saved.id()).withSelfRel();
        EntityModel<UserCreationResponse> entityModel = EntityModel.of(saved);
        entityModel.add(link);
        return entityModel;
    }

    public void sendValidationEmail(User user) throws MessagingException {
        final String token = generateAndSaveActivationToken(user);
        emailService
                .sendEmail(user.getEmail(), user.fullName(), confirmationUrl,
                        token, "Account Activation");
    }

    private String generateAndSaveActivationToken(User user) {
        final String generatedToken = generateActivationCode(6);
        Token token = TokenBuilder.builder()
                .withToken(generatedToken)
                .withCreatedAt(LocalDateTime.now())
                .withExpiresAt(LocalDateTime.now().plusMinutes(15))
                .withUser(user)
                .build();
        this.tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    private User hash(UserPostRequest userPostRequest) {
        User user = this.userMapper.toUser(userPostRequest);
        final String hash = passwordEncoder.encode(userPostRequest.hashPassword());
        user.setHashPassword(hash);
        return user;
    }
}
