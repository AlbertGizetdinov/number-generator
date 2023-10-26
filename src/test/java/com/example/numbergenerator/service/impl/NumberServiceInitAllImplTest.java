package com.example.numbergenerator.service.impl;

import com.example.numbergenerator.exception.NextNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberServiceInitAllImplTest {
    NumberServiceInitAllImpl numberServiceInitAll;
    String expectedRegEx = "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2} 116 RUS$";

    @BeforeEach
    void beforeEach() {
        numberServiceInitAll = new NumberServiceInitAllImpl();
    }

    @Test
    void generateRandomNumberTest() {
        assertTrue(numberServiceInitAll.generateRandomNumber().matches(expectedRegEx));
    }

    @Test
    void generateNextNumberTest() {
        assertThrows(NextNumberException.class, () -> numberServiceInitAll.generateNextNumber());
        numberServiceInitAll.generateRandomNumber();
        assertTrue(numberServiceInitAll.generateNextNumber().matches(expectedRegEx));
    }
}
