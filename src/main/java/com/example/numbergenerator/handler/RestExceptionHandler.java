package com.example.numbergenerator.handler;

import com.example.numbergenerator.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleUnauthorized(ServiceException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
                .header("Content-Type", "text/plain; charset=utf-8")
                .body(exception.getMessage());
    }
}
