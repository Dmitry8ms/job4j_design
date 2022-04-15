package ru.job4j.design.lsp.foodstore;

import java.util.Calendar;

public class ControlQuality {

    private int percent(Food food) {
        long start = food.getCreateDate().getTimeInMillis();
        long finish = food.getExpireDate().getTimeInMillis();
        long now = Calendar.getInstance().getTimeInMillis();
        return (int) (((double) (now - start) / (double) (finish - start)) * 100);
    }
    public Storage<Food> checkFood(Food food) {
        Storage<Food> storage = new Trash();
        int percent = percent(food);
        if (percent > 0 && percent < 25) {
            storage = new Warehouse();
        } else if (percent < 75) {
            storage = new Shop();
        } else if (percent < 100) {
            storage = new Shop();
            food.setDiscount(30);
        }
        return storage;
    }

    public static void main(String[] args) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, 0, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, 6, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.checkFood(cheese);
        storage.addToStorage(cheese);
        System.out.println(storage.getClass().getSimpleName().toString() + storage.inventory());
    }
}
