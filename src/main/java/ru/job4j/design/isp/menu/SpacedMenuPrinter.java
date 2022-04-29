package ru.job4j.design.isp.menu;

public class SpacedMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(i ->
                System.out.println(space(i.getNumber()) + i.getNumber() + " " + i.getName()));
    }

    private String space(String number) {
        StringBuilder tabs = new StringBuilder();
        for (char ch : number.toCharArray()) {
            if (ch == '.') {
                tabs.append('\t');
            }
        }
        return tabs.deleteCharAt(0).toString();
    }
}
