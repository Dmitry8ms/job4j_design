package ru.job4j.design.ocp;

/**
 * Принцип OCP нарушается, так как для расширения функционала giveAnimalSound() надо
 * дописывать код
 */
public class Main1 {
    public void giveAnimalSound() {
        new Tiger().giveRoar();
        new Cat().giveMeow();
    }
    public static void main(String[] args) {
        new Main1().giveAnimalSound();
    }

    public static class Tiger {
        public void giveRoar() {
            System.out.println("Roaring");
        }
    }

    public static class Cat {
        public  void giveMeow() {
            System.out.println("Meowing");
        }
    }
}
