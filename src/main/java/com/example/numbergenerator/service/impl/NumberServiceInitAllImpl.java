package com.example.numbergenerator.service.impl;

import com.example.numbergenerator.exception.MaxNumberException;
import com.example.numbergenerator.exception.NextNumberException;
import com.example.numbergenerator.model.Number;
import com.example.numbergenerator.service.NumberService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class NumberServiceInitAllImpl implements NumberService {
    private static final List<Character> CHARS = List.of('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х');
    private final List<Number> numbers = new LinkedList<>();
    private final Random random = new Random();
    private Number lastNumber;
    private Integer lastNumberId;

    public NumberServiceInitAllImpl() {
        lastNumber = new Number(CHARS.get(0), 0, CHARS.get(0), CHARS.get(0));
        numbers.add(lastNumber);
        int i = 1;
        while (i++ < 12 * 10 * 10 * 10 * 12 * 12) {
            lastNumber = getNextNumber();
            numbers.add(lastNumber);
        }
        lastNumber = null;
    }

    @Override
    public String generateRandomNumber() {
        lastNumberId = random.nextInt(numbers.size());
        lastNumber = numbers.get(lastNumberId);
        numbers.remove(lastNumber);
        return lastNumber.toString();
    }

    @Override
    public String generateNextNumber() {
        if (lastNumber == null || lastNumberId == null) {
            throw new NextNumberException("Метод /random ни разу не был вызван");
        }

        lastNumber = numbers.get(lastNumberId);
        numbers.remove(lastNumber);
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
}
