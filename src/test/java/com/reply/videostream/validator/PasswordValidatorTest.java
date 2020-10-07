package com.reply.videostream.validator;

import com.reply.videostream.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@DisplayName("Validate age")
public class PasswordValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void initialise(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("valid password")
    void validatePassword() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    @DisplayName("valid password")
    void validatePasswordWithNoUpperCase() {
        User user = new User("User1", "passwor1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("valid password")
    void validatePasswordWithNoNumber() {
        User user = new User("User1", "passworD", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }
}
