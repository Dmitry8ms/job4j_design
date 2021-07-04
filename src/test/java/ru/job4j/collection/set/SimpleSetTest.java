package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddStringAndIter() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("one");
        assertTrue(set.contains("one"));
        Iterator<String> iter = set.iterator();
        assertThat(iter.next(), is("one"));
        assertThat(iter.next(), is("two"));
        assertFalse(iter.hasNext());
    }


}