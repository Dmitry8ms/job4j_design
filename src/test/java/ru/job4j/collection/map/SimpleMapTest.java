package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void testPutWhenTrue() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        assertTrue(testMap.put(1, 1));
    }

    @Test
    public void testPutWhenFalse() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        assertFalse(testMap.put(1, 1));
    }

    @Test
    public void testPutWhenNull() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        assertFalse(testMap.put(null, 1));
    }

    @Test
    public void testGetWhenIs() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 2);
        assertThat(testMap.get(1), is(2));
    }

    @Test
    public void testGetWhenIsNot() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        assertNull(testMap.get(2));
    }

    @Test
    public void testGetWhenKeyNull() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        assertNull(testMap.get(null));
    }

    @Test
    public void testRemoveWhenTrue() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        assertTrue(testMap.remove(1));
    }

    @Test
    public void testRemoveWhenFalse() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        assertFalse(testMap.remove(2));
    }

    @Test
    public void testRemoveWhenNull() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        assertFalse(testMap.remove(null));
    }

    @Test
    public void testIteratorWhenTwo() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        testMap.put(10, 10);
        Iterator<Integer> iter = testMap.iterator();
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(10));
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIteratorWhenTwoAndOneRemove() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        testMap.put(10, 10);
        testMap.remove(10);
        Iterator<Integer> iter = testMap.iterator();
        assertThat(iter.next(), is(1));
        assertFalse(iter.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorWhenMod() {
        SimpleMap<Integer, Integer> testMap = new SimpleMap<>();
        testMap.put(1, 1);
        testMap.put(10, 10);
        testMap.remove(10);
        Iterator<Integer> iter = testMap.iterator();
        assertThat(iter.next(), is(1));
        testMap.put(10, 10);
        iter.hasNext();
    }
}