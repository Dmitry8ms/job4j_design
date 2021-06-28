package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container;
    private int pointer = 0;
    private int size = 0;

    public SimpleArray(int size) {
        container = (T[]) new Object[size];
        this.size = size;
    }

    public void add(T model) {
        container[Objects.checkIndex(pointer, size)] = model;
        pointer++;
    }

    public T get(int index) {
        return container[Objects.checkIndex(index, pointer)];
    }

    public void set(int index, T model) {
        container[Objects.checkIndex(index, pointer)] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, pointer);
        if (index != container.length - 1) {
            System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        }
        pointer--;
        container[pointer] = null;
    }

    public void printArray(SimpleArray<T> arr) {
        for (T e : arr.container) {
            System.out.println(e);
        }
        System.out.println("--");
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIter();
    }

    private class SimpleArrayIter implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < pointer;
        }

        @Override
        public T next() {
            return container[index++];
        }
    }

    public static void main(String[] args) {
        SimpleArray<Integer> myArray = new SimpleArray<>(3);
        myArray.add(5);
        myArray.add(6);
        myArray.add(7);
        //myArray.set(3, 8);
        myArray.printArray(myArray);
        myArray.remove(1);
        myArray.printArray(myArray);
        myArray.add(6);
        myArray.printArray(myArray);
        myArray.remove(0);
        myArray.printArray(myArray);
        System.out.println("get - " + myArray.get(1));
        //myArray.remove(2);
        Iterator<Integer> iter = myArray.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }


}

