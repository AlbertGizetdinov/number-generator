package com.example.numbergenerator.exception;

import org.springframework.http.HttpStatus;

public class MaxNumberException extends ServiceException {
    public MaxNumberException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
