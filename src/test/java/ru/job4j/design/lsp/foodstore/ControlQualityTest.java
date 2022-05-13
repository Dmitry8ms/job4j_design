package ru.job4j.design.lsp.foodstore;

import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
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
        startDate.roll(Calendar.YEAR, -1);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 5);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setDiscount(30);
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        assertEquals(distribute.getStorageList().get(0).inventory().get(0), cheese);
    }

    @Test
    public void shopWhen25andLessThan75() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 3);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        assertEquals(distribute.getStorageList().get(1).inventory().get(0), cheese);
    }

    @Test
    public void discountWhen75andLessThan100() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 1);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setDiscount(30);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        List<Food> shopList = distribute.getStorageList().get(1).inventory();
        assertThat(shopList.get(0).getPrice(), is(700.0));
    }

    @Test
    public void trashWhenExpired() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, -2);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        assertEquals(distribute.getStorageList().get(2).inventory().get(0), cheese);
    }

    @Test
    public void eachStorageContainsOne() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -1);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 8);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setDiscount(30);
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 5);
        var sausage = new Food();
        sausage.setName("Sausage");
        sausage.setPrice(2000.0);
        sausage.setDiscount(40);
        sausage.setCreateDate(startDate);
        sausage.setExpireDate(finishDate);
        startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, -2);
        var chocolate = new Food();
        chocolate.setName("Chocolate");
        chocolate.setPrice(800.0);
        chocolate.setCreateDate(startDate);
        chocolate.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        distribute.distributeFood(sausage);
        distribute.distributeFood(chocolate);
        assertArrayEquals(new int[] {1, 1, 1},
                new int[] {distribute.getStorageList().get(0).inventory().size(),
                        distribute.getStorageList().get(1).inventory().size(),
                        distribute.getStorageList().get(2).inventory().size()});
    }

    @Test
    public void warehouseFirstShopAfterResort() {
        List<Storage<Food>> storageList = List.of(new Warehouse(), new Shop(), new Trash());
        Calendar startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -1);
        Calendar finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 5);
        var cheese = new Food();
        cheese.setName("Cheese");
        cheese.setDiscount(30);
        cheese.setPrice(1000.0);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        var distribute = new ControlQuality(storageList);
        distribute.distributeFood(cheese);
        assertEquals(distribute.getStorageList().get(0).inventory().get(0), cheese);
        startDate = Calendar.getInstance();
        startDate.roll(Calendar.YEAR, -3);
        finishDate = Calendar.getInstance();
        finishDate.roll(Calendar.YEAR, 3);
        cheese.setCreateDate(startDate);
        cheese.setExpireDate(finishDate);
        distribute.resort();
        assertEquals(distribute.getStorageList().get(1).inventory().get(0), cheese);
    }
}