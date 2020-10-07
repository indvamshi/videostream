package com.reply.videostream.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom validator to validate Credict Card
 *
 */
@Documented
@Constraint(validatedBy = CreditCardValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckCreditCard {

    String message() default "Credit Card number must be {value} digits";

    long value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
