package ru.job4j.generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {


    private T[] container;
    private int pointer = 0;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray(int size) {
        container = (T[]) new Object[size];
        this.size = size;
    }

    public SimpleArray() {
        this.size = 10;
        container = (T[]) new Object[size];
    }

    public void add(T model) {
        if (pointer == size) {
            size *= 2;
            T[] newContainer = (T[]) new Object[size];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }
        container[Objects.checkIndex(pointer, size)] = model;
        pointer++;
        modCount++;
    }

    public int capacity() {
        return size;
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
        modCount++;
        container[pointer] = null;
    }

    @Override
    public String toString() {
        return Arrays.toString(container);
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIter();
    }

    private class SimpleArrayIter implements Iterator<T> {
        int index = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return index < pointer;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[index++];
        }
    }

    public static void main(String[] args) {
        SimpleArray<Integer> myArray = new SimpleArray<>(3);
        myArray.add(5);
        System.out.println(myArray);
        myArray.add(6);
        System.out.println(myArray);
        myArray.add(7);
        System.out.println(myArray);
        myArray.remove(1);
        System.out.println(myArray);
        myArray.add(6);
        System.out.println(myArray);
        myArray.remove(0);
        System.out.println(myArray);
        System.out.println("get - " + myArray.get(1));
        Iterator<Integer> iter = myArray.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        myArray.add(5);
        System.out.println(myArray);
        myArray.add(8);
        System.out.println(myArray);
    }


}

