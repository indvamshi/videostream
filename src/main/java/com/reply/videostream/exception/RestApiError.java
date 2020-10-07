package com.reply.videostream.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Custom class to show friendly error message to the user
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;

    RestApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestApiError(HttpStatus status, String message, String debugMessage) {
        this(status, message);
        this.debugMessage = debugMessage;
    }

}
