package ru.job4j.design.lsp.parkinglot;

public class Car extends Vehicle {
    private static final int SIZE = 1;
    @Override
    public int getSize() {
        return SIZE;
    }

}
