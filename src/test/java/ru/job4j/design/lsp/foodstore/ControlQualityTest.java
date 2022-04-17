package ru.job4j.design.lsp.foodstore;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void warehouseWhenLessThan25() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2023, Calendar.FEBRUARY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setDiscount(30);
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.distributeFood(storageList, cheese);
        assertEquals(storage.getClass(), Warehouse.class);
    }

    @Test
    public void shopWhenMoreThan25andLessThan75() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.JULY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.distributeFood(storageList, cheese);
        assertEquals(storage.getClass(), Shop.class);
    }

    @Test
    public void discountWhenMoreThan75andLessThan100() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.MAY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.distributeFood(storageList, cheese);
        List<Food> shopList = storage.inventory();
        assertThat(shopList.get(0).getPrice(), is(700.0));
    }

    @Test
    public void trashWhenExpired() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.APRIL, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.distributeFood(storageList, cheese);
        assertEquals(storage.getClass(), Trash.class);
    }

    @Test
    public void eachStorageContainsOne() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2023, Calendar.FEBRUARY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setDiscount(30);
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.JULY, 15);
        var sausage = new Food();
        sausage.setName("Sausage");
        sausage.setPrice(2000.0);
        sausage.setDiscount(40);
        sausage.setCreateDate(startDate);
        sausage.setExpireDate(finishDate);
        startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.APRIL, 15);
        var chocolate = new Food();
        chocolate.setName("Chocolate");
        chocolate.setPrice(800.0);
        chocolate.setCreateDate(startDate);
        chocolate.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> warehouse = distribute.distributeFood(storageList, cheese);
        Storage<Food> shop = distribute.distributeFood(storageList, sausage);
        Storage<Food> trash = distribute.distributeFood(storageList, chocolate);
        assertArrayEquals(new int[] {1, 1, 1}, new int[] {warehouse.inventory().size(),
        shop.inventory().size(), trash.inventory().size()});
    }
}