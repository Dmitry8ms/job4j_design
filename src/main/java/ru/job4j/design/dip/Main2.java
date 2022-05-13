package ru.job4j.design.dip;

/**
 * Возвращаемое значение конкретный класс, если позже потребуется получить Message или Book
 * или другую печатаемую сущность придется все переписывать.
 */
public class Main2 {
    public  static Letter getPrintable() {
        return new Letter();
    }

    public static void main(String[] args) {
        Letter letter = Main2.getPrintable();
    }
}

class Letter {

}
