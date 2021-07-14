package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> users = new HashMap<>();
        var user1 = new User("Ivan", 3, new GregorianCalendar(1939, Calendar.APRIL, 25));
        var user2 = new User("Ivan", 3, new GregorianCalendar(1939, Calendar.APRIL, 25));
        int hashCode = user1.hashCode();
        System.out.println("user1 hash-code - " + hashCode + " - " + Integer.toBinaryString(hashCode));
        int shift = hashCode >>> 16;
        int hash = shift ^ hashCode;
        int index = hash & 15;
        System.out.println("after bits shift - " + shift + " - " + Integer.toBinaryString(shift));
        System.out.println("after XOR - " + hash + " - " + Integer.toBinaryString(hash));
        System.out.println("15 in binary - " + Integer.toBinaryString(15));
        System.out.println("after & with 15 - " + index + " - " + Integer.toBinaryString(index));
        System.out.println("user2 hash-code - " + user2.hashCode());
        System.out.println("user1 equals user2 - " + user1.equals(user2));
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users);
        int hashCode1 = Float.hashCode(2.1f);
        int hashCode2 = Float.hashCode(2.15f);
        System.out.println(hashCode1 % 16);
        System.out.println(hashCode2 % 16);
    }
}
