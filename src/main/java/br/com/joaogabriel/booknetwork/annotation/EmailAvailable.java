package br.com.joaogabriel.booknetwork.annotation;

import br.com.joaogabriel.booknetwork.validate.EmailAvailableValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailAvailableValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAvailable {
    String message() default "Email already in use.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
