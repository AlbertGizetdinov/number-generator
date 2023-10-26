package com.example.numbergenerator.service.impl;

import com.example.numbergenerator.exception.NextNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberServiceImplTest {
    NumberServiceImpl numberServiceImpl;
    String expectedRegEx = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2} 116 RUS$";

    @BeforeEach
    void beforeEach() {
        numberServiceImpl = new NumberServiceImpl();
    }

    @Test
    void generateRandomNumberTest() {
        assertTrue(numberServiceImpl.generateRandomNumber().matches(expectedRegEx));
    }

    @Test
    void generateNextNumberTest() {
        assertThrows(NextNumberException.class, () -> numberServiceImpl.generateNextNumber());
        numberServiceImpl.generateRandomNumber();
        assertTrue(numberServiceImpl.generateNextNumber().matches(expectedRegEx));
    }
}
