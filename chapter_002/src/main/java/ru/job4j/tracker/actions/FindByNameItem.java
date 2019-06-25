package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

public class FindByNameItem extends BaseAction implements UserAction {
    
    public FindByNameItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявок по имени ----------------");
        String name = input.ask("Введите имя заявки: ");
        List<Item> items = tracker.findItemsByName(name);
        if (items.size() > 0) {
            System.out.println(" Заявки найдены: ");
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println(" Заявки с таким именем не найдены ");
        }
        System.out.println("------------ Конец --------------------------------");
    }
}
