package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (parentName == ROOT) {
            result = addParent(childName, actionDelegate);
        } else {
            result = addChild(parentName, childName, actionDelegate);
        }
        return result;
    }

    private boolean addParent(String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (findItem(childName).isEmpty()) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        }
        return result;
    }

    private boolean addChild(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        var parent = findItem(parentName);
        if (parent.isEmpty()) {
            return false;
        }
        MenuItem parentMenuItem = parent.get().getMenuItem();
        List<MenuItem> childrenList = parentMenuItem.getChildren();
        if (findItem(childName).isEmpty()) {
            childrenList.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> optMenuItem = findItem(itemName);
        return optMenuItem.map(ii -> new MenuItemInfo(ii.getMenuItem(), ii.getNumber()));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        var dfs = new DFSIterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return dfs.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itInf = dfs.next();
                return new MenuItemInfo(itInf.getMenuItem(), itInf.getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        var dfs = new DFSIterator();
        int flag = 0;
        Optional<ItemInfo> result = Optional.empty();
        while (dfs.hasNext()) {
            ItemInfo ii = dfs.next();
            if (ii.getMenuItem().getName().equals(name)) {
                result = Optional.of(ii);
                flag++;
            }
        }
        if (flag > 1) {
            throw new IllegalArgumentException("There is double in menu items with such name");
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(currentNumber); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        private MenuItem getMenuItem() {
            return menuItem;
        }

        private String getNumber() {
            return number;
        }
    }

}
