package ru.job4j.design.lsp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Parking {
    private int cars;
    private int trucks;
    private List<Vehicle> vehicles;
    public ParkingLot(int cars, int trucks) {
        this.cars = cars;
        this.trucks = trucks;
        vehicles = new ArrayList<>(cars + trucks);
    }
    @Override
    public boolean givePlace(Vehicle vehicle) {
        boolean result = false;
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == Car.SIZE && cars >= Car.SIZE) {
            cars -= Car.SIZE;
            vehicles.add(vehicle);
            result = true;
        } else if (vehicleSize > Car.SIZE && trucks > 0) {
            trucks -= 1;
            vehicles.add(vehicle);
            result = true;
        } else if (vehicleSize > Car.SIZE && cars >= vehicleSize) {
            cars -= vehicleSize;
            vehicles.add(vehicle);
            result = true;
        }
        return result;
    }

    public int getCars() {
        return cars;
    }

    public int getTrucks() {
        return trucks;
    }
}
