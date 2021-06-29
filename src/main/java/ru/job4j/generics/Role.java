package ru.job4j.generics;

public class Role extends Base {
    String role;
    public Role(String id, String name, String role) {
        super(id);
        this.role = role;
    }
}
