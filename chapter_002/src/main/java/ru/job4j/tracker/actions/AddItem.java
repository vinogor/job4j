package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class AddItem extends BaseAction implements UserAction {

    public AddItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        tracker.add(item);
        System.out.println("Новой заявке присвоен id = " + item.getId());
        System.out.println("------------ Новая заявка добавлена ---------------");
    }
}