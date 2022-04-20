package ru.job4j.design.lsp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Test
    public void trueIfGivePlace2Cars1Truck() {
        var park = new ParkingLot(2, 1);
        var car1 = new Car();
        var car2 = new Car();
        var truck1 = new Truck(2);
        park.givePlace(car1);
        park.givePlace(car2);
        assertTrue(park.givePlace(truck1));
    }

    @Test
    public void trueIfGivePlace2Trucks() {
        var park = new ParkingLot(2, 1);
        var truck1 = new Truck(2);
        var truck2 = new Truck(2);
        park.givePlace(truck1);
        assertTrue(park.givePlace(truck2));
    }
}