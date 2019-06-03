package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class DeleteItem implements UserAction {

    private int menuNum;
    private String menuInfo;

    public DeleteItem(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }

    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Удаление заявки ----------------------");
        String id = input.ask("Введите id заявки: ");
        boolean result = tracker.delete(id);
        if (result) {
            System.out.println(" Заявка удалена ");
        } else {
            System.out.println(" Заявки с таким id не найдено ");
        }
        System.out.println("------------ Конец --------------------------------");
    }

    @Override
    public String info() {
        return menuInfo;
    }
}
