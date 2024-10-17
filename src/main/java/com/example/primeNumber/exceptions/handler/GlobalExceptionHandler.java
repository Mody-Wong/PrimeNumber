package com.example.primeNumber.exceptions.handler;

import com.example.primeNumber.exceptions.NegativeNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handling custom exception NegativeNumberException
    @ExceptionHandler(NegativeNumberException.class)
    public ResponseEntity<String> handleNegativeNumberException(NegativeNumberException ex, WebRequest request) {
        // Here, you can return a response entity with a custom message and an HTTP status code.
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Optionally, you can handle other exceptions too.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}