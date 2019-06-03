package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindByIdItem extends BaseAction implements UserAction {
    
    public FindByIdItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
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
}
