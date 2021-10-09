package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> rsl = head;
        head = head.next;
        return rsl.value;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> oldHead = head;
        head = newNode;
        head.next = oldHead;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> prev = null;
        Node<T> cur = head;
        Node<T> nex = cur.next;
        while (nex != null) {
            cur.next = prev;
            prev = cur;
            cur = nex;
            nex = nex.next;
            cur.next = prev;
        }
        head = cur;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ForwardLinked<Integer> sl = new ForwardLinked<>();
        sl.add(1);
        System.out.println(sl.head.value);
        System.out.println(sl.head.next);
        sl.add(2);
        sl.add(3);
        System.out.println(sl.head.value);
        System.out.println(sl.head.next.value);
        System.out.println(sl.head.next.next.value);
    }
}
