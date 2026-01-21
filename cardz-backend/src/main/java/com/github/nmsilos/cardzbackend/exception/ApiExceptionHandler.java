package com.github.nmsilos.cardzbackend.exception;

import com.github.nmsilos.cardzbackend.exception.custom.InvalidUserException;
import com.github.nmsilos.cardzbackend.exception.custom.RequiredFieldMissingException;
import com.github.nmsilos.cardzbackend.exception.custom.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorMessage> invalidUserException(InvalidUserException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage()));
    }

    @ExceptionHandler(RequiredFieldMissingException.class)
    public ResponseEntity<ErrorMessage> requiredFieldMissingException(RequiredFieldMissingException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

}
