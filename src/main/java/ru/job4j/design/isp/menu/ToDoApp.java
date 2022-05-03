package ru.job4j.design.isp.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<String> toDoList = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String toDoLine = "";
            do {
                System.out.println("Please, enter new line (q - for quit): ");
                toDoLine = scanner.nextLine();
                toDoList.add(toDoLine);
            } while (!toDoLine.equals("q") && !toDoLine.equals("й"));
        }
        toDoList.remove(toDoList.size() - 1);
        new ToDoApp(new SimpleMenu(), new SpacedMenuPrinter()).printToDo(toDoList);
    }
}
