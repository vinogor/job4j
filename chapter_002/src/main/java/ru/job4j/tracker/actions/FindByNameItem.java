package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindByNameItem implements UserAction {

    private int menuNum;
    private String menuInfo;

    public FindByNameItem(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }

    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявок по имени ----------------");
        String name = input.ask("Введите имя заявки: ");
        Item[] items = tracker.findItemsByName(name);
        if (items.length > 0) {
            System.out.println(" Заявки найдены: ");
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println(" Заявки с таким именем не найдены ");
        }
        System.out.println("------------ Конец --------------------------------");
    }

    @Override
    public String info() {
        return menuInfo;
    }
}
