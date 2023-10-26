package com.example.numbergenerator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Number {
    private final int digits;
    private final char[] chars;
    private final String region = "116 RUS";

    public Number(char c1, int digits, char c2, char c3) {
        this.digits = digits;
        this.chars = new char[]{c1, c2, c3};
    }

    @Override
    public String toString() {
        return chars[0] +
                "0".repeat((int) (2 - Math.floor(Math.log10(digits > 0 ? digits : 1)))) +
                digits +
                chars[1] +
                chars[2] +
                " " +
                region;
    }
}
