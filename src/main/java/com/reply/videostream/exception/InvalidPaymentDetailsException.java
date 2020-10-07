package com.reply.videostream.exception;

/**
 * Custom exception thrown if {@code Payment} details does not exist in the system
 *
 */
public class InvalidPaymentDetailsException extends RuntimeException{

    public InvalidPaymentDetailsException(String message) {
        super(message);
    }
}
