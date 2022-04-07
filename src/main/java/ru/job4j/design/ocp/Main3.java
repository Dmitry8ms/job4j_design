package ru.job4j.design.ocp;

/**
 * Принцип OCP нарушается отсутствием гибкости в параметре метода usefulFunction()
 */
public class Main3 {

    public void usefulFunction(Assistant assistant) {
        assistant.assist();
    }

    public static class Assistant {
        public void assist() {
            System.out.println("very useful action");
        }
    }
}
