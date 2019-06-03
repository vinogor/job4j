package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindByIdItem implements UserAction {

    private int menuNum;
    private String menuInfo;

    public FindByIdItem(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }


    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по id -------------------");
        String id = input.ask("Введите id заявки: ");
        Item result = tracker.findItemById(id);
        if (result != null) {
            System.out.println(" Заявка найдена: " + result);
        } else {
            System.out.println(" Заявок с таким id не найдено");
        }
        System.out.println("------------ Конец --------------------------------");
    }

    @Override
    public String info() {
        return menuInfo;
    }
}
