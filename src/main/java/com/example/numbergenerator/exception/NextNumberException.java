package com.example.numbergenerator.exception;

import org.springframework.http.HttpStatus;

public class NextNumberException extends ServiceException {
    public NextNumberException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
