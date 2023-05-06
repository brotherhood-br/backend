package com.brotherhood.exception;

public class InvalidUserTokenException extends RuntimeException {
    public InvalidUserTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserTokenException(String message) {
        super(message);
    }
}
