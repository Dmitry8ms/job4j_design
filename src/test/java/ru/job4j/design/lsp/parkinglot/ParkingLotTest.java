package ru.job4j.design.lsp.parkinglot;

import org.junit.Ignore;
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

    @Ignore
    @Test
    public void testGivePlaceLogic() {
        var park = new ParkingLot(4, 2);
        var car1 = new Car();
        var truck1 = new Truck(5);
        var truck2 = new Truck(2);
        var truck3 = new Truck(3);
        assertTrue(park.givePlace(car1));
        assertTrue(park.givePlace(truck1));
        assertTrue(park.givePlace(truck2));
        assertTrue(park.givePlace(car1));
        assertFalse(park.givePlace(truck3));
        assertTrue(park.givePlace(truck2));
        assertFalse(park.givePlace(car1));
    }

    @Ignore
    @Test
    public void falseWhenGiveTruckPlaceToCar() {
        var park = new ParkingLot(0, 1);
        var car = new Car();
        assertFalse(park.givePlace(car));
    }
}