package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class UpdateItem extends BaseAction implements UserAction {
    
    public UpdateItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Изменение имеющейся заявки -----------");
        String id = input.ask("Введите id заявки: ");
        String name = input.ask("Введите новое имя заявки: ");
        String desc = input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        boolean result = tracker.replace(id, item);
        if (result) {
            System.out.println(" Заявка обновлена ");
        } else {
            System.out.println(" Заявки с таким id не найдено ");
        }
        System.out.println("------------ Конец --------------------------------");
    }
}