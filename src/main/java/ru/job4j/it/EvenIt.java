package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] numbers;
    private int pointer = 0;

    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public boolean hasNext() {
        while (pointer < numbers.length && numbers[pointer] % 2 != 0) {
            pointer++;
        }
        return pointer < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[pointer++];
    }
}
