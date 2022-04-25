package ru.job4j.design.isp;

/**
 * Методы в интерфейсе подобраны с нарушением ISP так как у некоторых средств передвижения
 * может не быть задней скорости, или например не быть двигателя.
 */
public interface Vehicle {
    void goForward(int speed);
    void goBack(int speed);
    boolean startEngine();
}
