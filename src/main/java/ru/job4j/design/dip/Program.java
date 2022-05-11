package ru.job4j.design.dip;

/**
 * Так же все очень жестко Program зависит от PrintBook, PrintBook зависит от Book. Без интерфейсов
 * нарушается принцип DIP
 */
public class Program {
    public static void main(String[] args) {
        PrintBook consolePrint = new PrintBook();
        consolePrint.printBook(new Book());
    }
}

class PrintBook {
    public void printBook(Book book) {
        System.out.println(book);
    }
}

class Book {

}