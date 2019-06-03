package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class DeleteItem extends BaseAction implements UserAction {
    
    public DeleteItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
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

}
