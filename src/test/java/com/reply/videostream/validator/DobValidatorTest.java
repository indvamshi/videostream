package com.reply.videostream.validator;

import com.reply.videostream.exception.UnderAgeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Validate Age")
public class DobValidatorTest {

    @InjectMocks
    private DobValidator dobValidator;

    @Test
    @DisplayName("user is 18 yrs old")
    void userIs18YrsOld() {
        dobValidator.validateAge("1989-07-16T19:23:51");

        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("user is less than 18 yrs old")
    void userIsLessThan18YrsOld() {
        Assertions.assertThrows(UnderAgeException.class,
                                () -> dobValidator.validateAge("2018-07-16T19:23:51"));
    }
}
