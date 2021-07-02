package ru.job4j.collection;

import java.util.Iterator;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;
    public T poll() {
        if (outSize == 0) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
                outSize++;
            }
            inSize = 0;
        }
        T rsl = out.pop();
        outSize--;
        return rsl;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.poll());
        queue.push(4);
        System.out.println(queue.poll());
        queue.push(5);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}