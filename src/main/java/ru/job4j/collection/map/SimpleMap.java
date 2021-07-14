package ru.job4j.collection.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return (hashCode >>> capacity) ^ hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        count = 0;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                this.put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null || !table[index].key.equals(key)) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        if (table[index] == null || !table[index].key.equals(key)) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int fixMod = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != fixMod) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (modCount != fixMod) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{key = " + key
                    + ", value = " + value + "}";
        }
    }

    public static void main(String[] args) {
        SimpleMap<Integer, String> testMap = new SimpleMap<>();
        for (int i = 0; i < 6; i++) {
            testMap.put(i + 1, String.valueOf(i + 1));
        }
        System.out.println(testMap);
        SimpleMap<String, Integer> testMap2 = new SimpleMap<>();
        for (int i = 0; i < 5; i++) {
            testMap2.put(String.valueOf(i + 1), i + 1);
        }
        System.out.println(testMap2);
        SimpleMap<String, Integer> testMap3 = new SimpleMap<>();
        testMap3.put("Dmitriy", 7);
        testMap3.put("Artyom", 5);
        System.out.println(testMap3);
        System.out.println(testMap3.get("Dmitriy"));
        testMap3.remove("Artyom");
        System.out.println(testMap3);
        testMap3.put("Polina", 10);
        Iterator<String> iter = testMap3.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
