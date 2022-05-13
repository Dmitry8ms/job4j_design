package ru.job4j.design.lsp.foodstore;

import java.util.Calendar;
import java.util.List;

public interface Storage<T> {
    boolean addToStorage(T item);
    List<T> inventory();
    boolean accept(T item);
    default int percent(Food food) {
        long start = food.getCreateDate().getTimeInMillis();
        long finish = food.getExpireDate().getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        return (int) (((double) (now - start) / (double) (finish - start)) * 100);
    }
    void clear();
}
