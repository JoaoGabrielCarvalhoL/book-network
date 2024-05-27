package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.model.entity.Token;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.auth.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.auth.JWTTokenResponse;
import br.com.joaogabriel.booknetwork.repository.TokenRepository;
import br.com.joaogabriel.booknetwork.repository.UserRepository;
import br.com.joaogabriel.booknetwork.security.jwt.service.impl.JwtService;
import br.com.joaogabriel.booknetwork.service.AuthenticationService;
import br.com.joaogabriel.booknetwork.service.EmailService;
import br.com.joaogabriel.booknetwork.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService,
                                     TokenRepository tokenRepository, UserService userService,
                                     UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public JWTTokenResponse authenticate(AuthenticateRequest authRequest) {
        log.log(Level.INFO, "Authenticate user: {1}", authRequest.email());
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(),
                        authRequest.hashPassword()));
        Map<String, Object> claims = new HashMap<String, Object>();
        User user = ((User) authentication.getPrincipal());
        claims.put("fullName", user.fullName());
        return new JWTTokenResponse(jwtService.generateToken(claims, user));

    }

    @Override
    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceAccessException("Token invalid."));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            userService.sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send.");
        }
        User user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(ResourceNotFoundException::new);
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

    }
}
