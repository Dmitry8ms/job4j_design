package ru.job4j.design.lsp.foodstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop implements Storage<Food> {
    private List<Food> storageList = new ArrayList<Food>();

    @Override
    public boolean addToStorage(Food item) {
        boolean result = accept(item);
        if (result) {
            storageList.add(item);
            int percent = percent(item);
            if (percent >= 75) {
                double discounted = item.getPrice() - item.getPrice() * item.getDiscount() / 100;
                item.setPrice(discounted);
            }
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
        return percent >= 25 && percent < 100;
    }

    @Override
    public void clear() {
        storageList.clear();
    }
}
