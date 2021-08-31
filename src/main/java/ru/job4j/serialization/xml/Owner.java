package ru.job4j.serialization.xml;

public class Owner {
    private final String name;
    private final int age;
    private final String license;

    public Owner(String name, int age, String license) {
        this.name = name;
        this.age = age;
        this.license = license;
    }
}
