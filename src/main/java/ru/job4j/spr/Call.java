package ru.job4j.spr;

/**
 * Принцип единой ответственности нарушается - надо разделить на два интерфейса, также нарушается
 * принцип сегрегации интерфейсов.
 */

public interface Call {
    public void callTaxi();
    public void logCall(String info);
}
