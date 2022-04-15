package ru.job4j.design.lsp.foodstore;

import java.util.List;

public interface Storage<T> {
    public void addToStorage(T item);
    public List<T> inventory();
}
