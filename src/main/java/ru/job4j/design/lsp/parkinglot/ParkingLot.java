package ru.job4j.design.lsp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Parking {
    private int cars;
    private int trucks;
    private List<Vehicle> vehicles = new ArrayList<>();
    public ParkingLot(int cars, int trucks) {
        this.cars = cars;
        this.trucks = trucks;
    }
    @Override
    public boolean givePlace(Vehicle vehicle) {
        return true;
    }
}
