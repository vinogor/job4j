package ru.job4j.tracker.menuElements;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class FindAllItem implements UserAction {

    private int menuNum;
    private String menuInfo;

    public FindAllItem(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }

    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Вывод содержимого хранилища ----------");
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("------------ Конец --------------------------------");    }

    @Override
    public String info() {
        return menuInfo;
    }
}
