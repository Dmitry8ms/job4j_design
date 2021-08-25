package ru.job4j.exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Shell {
    private Deque<String> deque = new ArrayDeque<>();

    public void cd(String path) {
        String[] array = path.split("/");
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            if (temp.equals("")) {
                continue;
            }
            if (deque.contains(temp)) {
                deque.pollLast();
            }
            if (!temp.equals("..")) {
                deque.offerFirst(temp);
            } else {
                deque.pollLast();
            }
        }
        if (deque.isEmpty()) {
            deque.offerFirst("/");
        }
    }

    public String pwd() {
        StringBuilder sb = new StringBuilder();
        if (deque.contains("/")) {
            sb.append(deque.pollFirst());
        }
        while (!deque.isEmpty() && !deque.contains("/")) {
            sb.append("/").append(deque.pollLast());
        }
        return new String(sb);
    }
}
