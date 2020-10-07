package com.reply.videostream.validator;

import com.reply.videostream.model.Payment;
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
public class PaymentTest {

    private static Validator validator;

    @BeforeAll
    public static void initialise(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("valid payment")
    void validatePayment() {
        Payment payment = new Payment("", 100);

        Set<ConstraintViolation<Payment>> validate = validator.validate(payment);

        Assertions.assertTrue(validate.isEmpty());
    }

    @Test
    @DisplayName("invalid amount")
    void invalidAmount() {
        Payment payment = new Payment("", 1987);

        Set<ConstraintViolation<Payment>> validate = validator.validate(payment);

        Assertions.assertFalse(validate.isEmpty());
    }
}
