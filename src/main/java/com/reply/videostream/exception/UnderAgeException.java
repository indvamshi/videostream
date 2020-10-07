package com.reply.videostream.exception;

/**
 * Custom exception thrown when user is below 18 years old
 *
 */
public class UnderAgeException extends RuntimeException{

    public UnderAgeException(String message) {
        super(message);
    }
}
