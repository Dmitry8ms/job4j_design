package ru.job4j.design.isp.menu;

import java.util.List;

public class ToDoApp {
    private Menu toDoMenu;
    private MenuPrinter printer;
    private static final ActionDelegate STUB_ACTION = System.out::println;
    public ToDoApp(Menu toDoMenu, MenuPrinter printer) {
        this.toDoMenu = toDoMenu;
        this.printer = printer;
    }

    public void printToDo(List<String> toDoList) {
        if (toDoList == null) {
            throw new IllegalArgumentException("List should be not null");
        }
        for (String toDo : toDoList) {
            toDoMenu.add(Menu.ROOT, toDo, STUB_ACTION);
        }
        printer.print(toDoMenu);
    }

    public static void main(String[] args) {
        List<String> toDoList = List.of("Do morning exercises", "Have breakfast", "Learn to Code");
        new ToDoApp(new SimpleMenu(), new SpacedMenuPrinter()).printToDo(toDoList);
    }
}
