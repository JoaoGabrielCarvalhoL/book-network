package br.com.joaogabriel.booknetwork.validate;

import br.com.joaogabriel.booknetwork.annotation.EmailAvailable;
import br.com.joaogabriel.booknetwork.repository.UserRepository;
import io.jsonwebtoken.lang.Objects;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailAvailableValidator implements ConstraintValidator<EmailAvailable, String> {

    private final UserRepository userRepository;

    public EmailAvailableValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(Objects.isEmpty(value)) return false;
        return !userRepository.existsByEmail(value);
    }


}
