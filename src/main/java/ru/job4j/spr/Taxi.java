package ru.job4j.spr;

/**
 * Принцип единой ответственности нарушается из-за метода callTaxi который накладывает на класс
 * дополнительную ответственность.
 */

public class Taxi {
    String plateNumber;
    TaxiLevel taxiClass;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public TaxiLevel getTaxiClass() {
        return taxiClass;
    }

    public void setTaxiClass(TaxiLevel taxiClass) {
        this.taxiClass = taxiClass;
    }

    public void callTaxi() {

    }

}
