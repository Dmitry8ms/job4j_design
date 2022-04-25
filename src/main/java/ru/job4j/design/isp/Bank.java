package ru.job4j.design.isp;

/**
 * Если мы используем интерфейс Bank в классе копилка MoneyBox, то нам придется заглушить
 * метод checkClient(). Появляется лишняя зависимость.
 */
public interface Bank {
    boolean deposit(double sum);
    boolean withdraw(double sum);
    boolean checkClient();
}
