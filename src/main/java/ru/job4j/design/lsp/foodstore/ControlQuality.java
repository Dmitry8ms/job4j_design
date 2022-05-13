package ru.job4j.design.lsp.foodstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {
    private List<Storage<Food>> storageList;
    public ControlQuality(List<Storage<Food>> storageList) {
        this.storageList = storageList;
    }
    public void distributeFood(Food food) {
        for (Storage<Food> store : storageList) {
            if (store.accept(food)) {
                store.addToStorage(food);
            }
        }
    }

    public  void resort() {
        List<Food> allProducts = new ArrayList<>();
        for (Storage<Food> store : storageList) {
            List<Food> products = store.inventory();
            store.clear();
            allProducts.addAll(products);
        }
        for (Food product : allProducts) {
            distributeFood(product);
        }
    }

    public List<Storage<Food>> getStorageList() {
        return storageList;
    }

    public static void main(String[] args) {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, 0, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2024, 4, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        for (Storage storage : distribute.getStorageList()) {
            if (storage.inventory().size() > 0) {
                System.out.println(storage.getClass().getSimpleName());
                System.out.println(storage.inventory());
            }
        }
        finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.DECEMBER, 15);
        cheese.setExpireDate(finishDate);
        distribute.resort();
        for (Storage storage : distribute.getStorageList()) {
            if (storage.inventory().size() > 0) {
                System.out.println(storage.getClass().getSimpleName());
                System.out.println(storage.inventory());
            }
        }
    }
}
