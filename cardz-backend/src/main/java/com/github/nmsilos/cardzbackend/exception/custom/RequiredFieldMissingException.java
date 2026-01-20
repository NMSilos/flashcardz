package com.github.nmsilos.cardzbackend.exception.custom;

public class RequiredFieldMissingException extends RuntimeException {
    public RequiredFieldMissingException(String message) {
        super(message);
    }
}
