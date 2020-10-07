package com.reply.videostream.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom validator to validate password
 *
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPassword {

    String message() default "Password minimum length must be 8, at least one upper case letter and number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
