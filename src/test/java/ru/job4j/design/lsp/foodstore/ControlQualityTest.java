package ru.job4j.design.lsp.foodstore;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void warehouseWhenLessThan25() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2023, Calendar.FEBRUARY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.checkFood(cheese);
        assertEquals(storage.getClass(), Warehouse.class);
    }

    @Test
    public void shopWhenMoreThan25andLessThan75() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.JULY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.checkFood(cheese);
        assertEquals(storage.getClass(), Shop.class);
    }

    @Test
    public void discountWhenMoreThan75andLessThan100() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2022, Calendar.JANUARY, 10);
        Calendar finishDate = Calendar.getInstance();
        finishDate.set(2022, Calendar.MAY, 15);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality();
        Storage<Food> storage = distribute.checkFood(cheese);
        storage.addToStorage(cheese);
        List<Food> shopList = storage.inventory();
        assertThat(shopList.get(0).getDiscount(), is(30));
    }

    @Test
    public void trashWhenExpired() {
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
        Storage<Food> storage = distribute.checkFood(cheese);
        assertEquals(storage.getClass(), Trash.class);
    }
}