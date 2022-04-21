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
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == Car.SIZE && cars - Car.SIZE >= 0) {
            cars -= Car.SIZE;
            vehicles.add(vehicle);
            return true;
        } else if (vehicleSize > Car.SIZE && trucks > 0) {
            trucks -= 1;
            vehicles.add(vehicle);
            return true;
        } else if (vehicleSize > Car.SIZE && cars - vehicleSize >= 0) {
            cars -= vehicleSize;
            vehicles.add(vehicle);
            return true;
        }
        return false;
    }

    public int getCars() {
        return cars;
    }

    public int getTrucks() {
        return trucks;
    }
}
