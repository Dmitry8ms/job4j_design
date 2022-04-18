package ru.job4j.design.lsp.parkinglot;

import java.util.List;

public interface Parking {
    public List<Place> givePlace(Car car);
}
