package ru.job4j.design.lsp.foodstore;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    public Storage<Food> distributeFood(List<Storage<Food>> storageList, Food food) {
        Storage<Food> storage = new Trash();
        for (Storage<Food> store : storageList) {
            if (store.accept(food)) {
                store.addToStorage(food);
                storage = store;
            }
        }
        return storage;
    }

    public static void main(String[] args) {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, 0, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2023, 4, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.distributeFood(storageList, cheese);
        System.out.println(storage.getClass().getSimpleName().toString() + storage.inventory());
    }
}
