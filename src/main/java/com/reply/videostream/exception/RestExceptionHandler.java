package com.reply.videostream.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling exceptions
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getObjectName() + ":" + error.getDefaultMessage())
                .collect(Collectors.toList());
        return buildResponseEntity(new RestApiError(status.BAD_REQUEST, ex.getBindingResult().getObjectName(), errorList.toString()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException exp) {
        return buildResponseEntity(new RestApiError(HttpStatus.BAD_REQUEST, exp.getMessage()));
    }

    @ExceptionHandler(EntityExistsException.class)
    private ResponseEntity<Object> handleEntityExists(EntityExistsException exp) {
        return buildResponseEntity(new RestApiError(HttpStatus.CONFLICT, exp.getMessage()));
    }

    @ExceptionHandler(UnderAgeException.class)
    private ResponseEntity<Object> handleUnderAge(UnderAgeException exp) {
        RestApiError apiError = new RestApiError(HttpStatus.FORBIDDEN, exp.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidPaymentDetailsException.class)
    private ResponseEntity<Object> handleInvalidPayment(InvalidPaymentDetailsException exp) {
        return buildResponseEntity(new RestApiError(HttpStatus.NOT_FOUND, exp.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleAllErrors(Exception exp) {
        return buildResponseEntity(new RestApiError(HttpStatus.BAD_REQUEST, exp.getMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(RestApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
