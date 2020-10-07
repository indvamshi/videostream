package com.reply.videostream.validator;

import com.reply.videostream.exception.UnderAgeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Business validator to valiate if user is > 18 yrs.
 *
 */
@Component
public class DobValidator {

    private final Log LOGGER = LogFactory.getLog(getClass());

    public void validateAge(String dob) {
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime ageDate = LocalDateTime.parse(dob, dateTimeFormatter);

            long age = ChronoUnit.YEARS.between(ageDate, LocalDateTime.now());
            if(age < 18) {
                LOGGER.error("User is below 18 years old");
                throw new UnderAgeException("You must be 18 years old");
            }
        } catch (DateTimeParseException exp) {
            LOGGER.error(String.format("Unable to parse date : %s", dob));
            throw new RuntimeException(String.format("Date %s cannot be parsed. Date should be in ISO 8601 format", dob));
        }
    }
}
