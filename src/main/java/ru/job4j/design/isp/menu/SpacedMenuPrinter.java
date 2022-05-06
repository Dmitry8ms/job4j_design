package ru.job4j.design.isp.menu;

public class SpacedMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(i ->
                System.out.println(space(i.getNumber()) + i.getNumber() + " " + i.getName()));
    }

    private String space(String number) {
        String[] chuncks = number.split("\\.");
        return "\t".repeat(chuncks.length - 1);
    }
}
