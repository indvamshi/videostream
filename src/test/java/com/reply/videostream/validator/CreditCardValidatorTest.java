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

@DisplayName("Validate Credit Card Number")
public class CreditCardValidatorTest {

    private static Validator validator;

    @BeforeAll
    public static void initialise(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("valid credit card number")
    void validCreditCardNumber() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51", "1234 5678 1012 1314");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());

        new User("User1", "passwoR1", "test@gmail.com",
                 "1990-07-16T19:23:51", "1234567810121314");

        validate = validator.validate(user);

        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    @DisplayName("Invalid credit card number")
    void InValidCreditCardNumber() {
        User user = new User("User1", "passwoR1", "test@gmail.com",
                             "1990-07-16T19:23:51", "12345678112131");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        Assertions.assertFalse(validate.isEmpty());
    }
}
