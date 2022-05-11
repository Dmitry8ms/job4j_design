package ru.job4j.design.dip;

/**
 * нарушение DIP в зависимости модуля верхнего уровня от модуля нижнего уровня
 */
public class Main {
    public static void main(String[] args) {
        var reporter = new Reporter();
        reporter.sendReports();
    }
}

class Reporter {
    public void sendReports() {

    }
}

