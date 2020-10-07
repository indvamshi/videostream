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

@DisplayName("Validate User")
public class UserTest {

    private static Validator validator;

    @BeforeAll
    public static void initialise(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("valid user")
    void validateUser() {
        User user = new User("User", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());

        user.setUserName("User12Test");
        validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());

        user.setUserName("User1");
        validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());


        user.setUserName("1User");
        validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    @DisplayName("username with special characters")
    void validateUsenameWithSpecialCharacters() {
        User user = new User("User1-", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("username with spaces")
    void validateUsenameWithSpaces() {
        User user = new User("User name", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("username is null")
    void validateWhenUsernameIsNull() {
        User user = new User(null, "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("username is blank")
    void validateWhenUsernameIsEmpty() {
        User user = new User("", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("email with invalid format")
    void validateEmailWithInvalidFormat() {
        User user = new User("User name", "passwoR1", "test-gmail.com",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("email is null")
    void validateWhenEmailIsNull() {
        User user = new User(null, "passwoR1", null,
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("email is blank")
    void validateWhenEmailIsEmpty() {
        User user = new User("", "passwoR1", "",
                             "1990-07-16T19:23:51");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

    @Test
    @DisplayName("dob is empty")
    void validateWhenDobIsEmpty() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "", "");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }

}
