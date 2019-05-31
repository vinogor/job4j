package ru.job4j.tracker.menuElements;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class AddItem implements UserAction {

    private int menuNum;
    private String menuInfo;

    public AddItem(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }

    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        tracker.add(item);
        System.out.println("Новой заявке присвоен id = " + item.getId());
        System.out.println("------------ Новая заявка добавлена -----------");
    }

    @Override
    public String info() {
        return menuInfo;
    }
}