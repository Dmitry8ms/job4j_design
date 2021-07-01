package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private int modCount = 0;
    private int size = 0;
    private Node first;
    private Node last;

    private void linkLast(E e) {
        final Node<E> oldLast = last;
        final Node<E> newLast = new Node<>(oldLast, e, null);
        last = newLast;
        if (oldLast == null) {
            first = newLast;
        } else {
            oldLast.next = newLast;
        }
    }
    @Override
    public void add(E value) {
        linkLast(value);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkIter();
    }

    private class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;
        private Node(Node<E> prev, E element,  Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private class SimpleLinkIter implements Iterator<E> {
        private int index = 0;
        private int expectedModCount = modCount;
        private Node<E> current = first;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E rsl = current.element;
            current = current.next;
            index++;
            return rsl;
        }
    }

    @Override
    public String toString() {
        Iterator<E> iter = this.iterator();
        StringBuilder builder = new StringBuilder("[");
        while (iter.hasNext()) {
            builder.append(iter.next() + " ");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> myArray = new SimpleLinkedList<>();
        myArray.add(5);
        System.out.println(myArray);
        myArray.add(6);
        myArray.add(7);
        System.out.println(myArray);
        System.out.println("get - " + myArray.get(2));
        //myArray.remove(2);
        Iterator<Integer> iter = myArray.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}