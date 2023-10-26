package com.example.numbergenerator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {
    Number number;

    @BeforeEach
    void beforeEach() {
        number = new Number('А', 116,'А', 'А');
    }

    @Test
    void toStringTest() {
        assertEquals("А116АА 116 RUS", number.toString());
    }
}
