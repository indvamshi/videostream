package com.reply.videostream.exception;

/**
 * Custom exception thrown if entity already exist in the system
 *
 */
public class EntityExistsException extends RuntimeException{

    public EntityExistsException(String message) {
        super(message);
    }
}
