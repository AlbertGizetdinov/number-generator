package com.example.numbergenerator.controller;

import com.example.numbergenerator.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberController {
    private final NumberService numberServiceImpl;

    @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRandomNumber() {
        return numberServiceImpl.generateRandomNumber();
    }

    @GetMapping(value = "/next", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getNextNumber() {
        return numberServiceImpl.generateNextNumber();
    }
}
