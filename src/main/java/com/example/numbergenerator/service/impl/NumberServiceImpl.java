package com.example.numbergenerator.service.impl;

import com.example.numbergenerator.exception.MaxNumberException;
import com.example.numbergenerator.exception.NextNumberException;
import com.example.numbergenerator.exception.NumbersOverException;
import com.example.numbergenerator.model.Number;
import com.example.numbergenerator.service.NumberService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class NumberServiceImpl implements NumberService {
    private static final List<Character> CHARS = List.of('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х');
    private final Set<Number> numbers = new HashSet<>();
    private final Random random = new Random();
    private Number lastNumber;

    @Override
    public String generateRandomNumber() {
        do {
            if (numbers.size() == 12 * 10 * 10 * 10 * 12 * 12) {
                throw new NumbersOverException("Номера закончились, приходите позже ;)");
            }
            lastNumber = new Number(getRandomChar(), getRandomDigits(), getRandomChar(), getRandomChar());
        } while (numbers.contains(lastNumber));
        numbers.add(lastNumber);
        return lastNumber.toString();
    }

    @Override
    public String generateNextNumber() {
        if (lastNumber == null) {
            throw new NextNumberException("Метод /random ни разу не был вызван");
        }

        do {
            lastNumber = getNextNumber();
        } while (numbers.contains(lastNumber));
        numbers.add(lastNumber);
        return lastNumber.toString();
    }

    private Number getNextNumber() {
        char[] chars = lastNumber.getChars();
        if (lastNumber.getDigits() != 999) {
            return new Number(chars[0], lastNumber.getDigits() + 1, chars[1], chars[2]);
        }

        if (chars[2] != CHARS.get(CHARS.size() - 1)) {
            return new Number(chars[0], 0, chars[1], CHARS.get(CHARS.indexOf(chars[2]) + 1));
        }

        if (chars[1] != CHARS.get(CHARS.size() - 1)) {
            return new Number(chars[0], 0, CHARS.get(CHARS.indexOf(chars[1]) + 1), CHARS.get(0));
        }

        if (chars[0] != CHARS.get(CHARS.size() - 1)) {
            return new Number(CHARS.get(CHARS.indexOf(chars[0]) + 1), 0, CHARS.get(0), CHARS.get(0));
        }

        throw new MaxNumberException("Вы достигли максимального номера " + lastNumber);
    }

    private char getRandomChar() {
        return CHARS.get(random.nextInt(CHARS.size()));
    }

    private int getRandomDigits() {
        return random.nextInt(1000);
    }
}
