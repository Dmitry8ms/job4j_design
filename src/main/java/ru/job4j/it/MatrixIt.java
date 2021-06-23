package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.length > row || data[row].length > column;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        //System.out.println("row - " + row);
        //System.out.println("column - " + column);
        if (column != data[row].length) {
            System.out.println(data[row][column++]);
            return data[row][column++];
        }
        column = 0;
        return data[++row][column];
    }
}
