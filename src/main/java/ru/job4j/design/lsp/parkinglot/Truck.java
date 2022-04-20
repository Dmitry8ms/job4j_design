package ru.job4j.design.lsp.parkinglot;

public class Truck extends Vehicle {

    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
