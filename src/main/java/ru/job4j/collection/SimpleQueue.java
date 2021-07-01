package ru.job4j.collection;

import java.util.Iterator;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;
    public T poll() {
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }

        T rsl = out.pop();
        size--;
        for (int i = 0; i < size; i++) {
            in.push(out.pop());
        }

        return rsl;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.poll());
        queue.push(3);
        System.out.println(queue.poll());
    }
}