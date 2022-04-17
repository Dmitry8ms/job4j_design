package ru.job4j.design.lsp.foodstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trash implements Storage<Food> {

    private List<Food> storageList = new ArrayList<Food>();

    @Override
    public boolean addToStorage(Food item) {
        boolean result = accept(item);
        if (result) {
            storageList.add(item);
        }
        return result;
    }

    @Override
    public List<Food> inventory() {
        return List.copyOf(storageList);
    }

    @Override
    public boolean accept(Food item) {
        int percent = percent(item);
        return percent >= 100;
    }
}
