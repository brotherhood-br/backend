package com.brotherhood.exception;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;


public class InvalidUserTokenException extends HttpStatusException {
    public InvalidUserTokenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
