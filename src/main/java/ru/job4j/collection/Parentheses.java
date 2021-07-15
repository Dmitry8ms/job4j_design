package ru.job4j.collection;

/* Реализовать метод проверки корректности открытых и закрытых скобок.

Например, ()(()((()))) - true, ()) - false */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parentheses {
    public static boolean valid(char[] data) {
        //List<Character> prs = new ArrayList<>();
        int flag = 0;
        for (char ch : data) {
            if (ch == '(') {
                flag++;
            } else if (ch == ')') {
                flag--;
            }
            if (flag < 0) {
                return  false;
            }
        }
        return flag == 0;
    }
}