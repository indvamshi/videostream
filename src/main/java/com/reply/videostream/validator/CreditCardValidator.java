package com.reply.videostream.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom validator implementation class to validate Credit Card
 *
 */
public class CreditCardValidator implements ConstraintValidator<CheckCreditCard, String> {

    private final Log LOGGER = LogFactory.getLog(getClass());

    private Pattern pattern = Pattern.compile("\\d{16}$");

    @Override
    public void initialize(CheckCreditCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(String ccNumber, ConstraintValidatorContext context) {
        LOGGER.debug("validating credit card number");
        if(StringUtils.isEmpty(ccNumber)) {
            return true;
        }
        Matcher matcher = pattern.matcher(ccNumber.replaceAll("\\s", ""));

        return matcher.matches();
    }
}
