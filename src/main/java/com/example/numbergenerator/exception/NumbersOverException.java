package com.example.numbergenerator.exception;

import org.springframework.http.HttpStatus;

public class NumbersOverException extends ServiceException {
    public NumbersOverException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
