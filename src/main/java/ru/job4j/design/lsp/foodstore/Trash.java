package ru.job4j.design.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage<Food> {

    private List<Food> storageList = new ArrayList<Food>();

    @Override
    public void addToStorage(Food item) {
        storageList.add(item);
    }

    @Override
    public List<Food> inventory() {
        return this.storageList;
    }
}
