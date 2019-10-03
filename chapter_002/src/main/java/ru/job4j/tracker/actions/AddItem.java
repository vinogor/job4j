package ru.job4j.tracker.actions;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;

import java.util.function.Consumer;

public class AddItem extends BaseAction implements UserAction {

    public AddItem(int menuNum, String menuInfo, Consumer<String> output) {
            super(menuNum, menuInfo, output);
    }

    @Override
    public void execute(Input input, ITracker tracker) {

        output.accept("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        tracker.add(item);
        output.accept("Новой заявке присвоен id = " + item.getId());
        output.accept("------------ Новая заявка добавлена ---------------");
    }
}