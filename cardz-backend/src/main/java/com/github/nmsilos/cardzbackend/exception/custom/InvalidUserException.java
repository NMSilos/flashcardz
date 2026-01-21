package com.github.nmsilos.cardzbackend.exception.custom;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
