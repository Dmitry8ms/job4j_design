package ru.job4j.design.ocp;

/**
 * Принцип OCP нарушается так как мы не можем расширить класс наследованием с добавлением
 * функционала
 */
public final class Main2 {
    public void someFunctionality() {
        System.out.println("I am doing something useful here");
    }
}
