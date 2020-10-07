package com.reply.videostream.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validator implementation class to validate password
 *
 */
public class PasswordValidator implements ConstraintValidator<CheckPassword, String> {

    private final Log LOGGER = LogFactory.getLog(getClass());

    @Override
    public void initialize(CheckPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LOGGER.debug("Validating password");
        if(StringUtils.isEmpty(value)) {
            return false;
        }
        if(value.length() < 8) {
            return false;
        }
        if(value.chars().filter(Character::isUpperCase).count() == 0){
            return false;
        }
        if(value.chars().filter(Character::isDigit).count() == 0) {
            return false;
        }

        return true;
    }
}
