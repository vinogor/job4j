package ru.job4j.tracker.actions;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;
import java.util.function.Consumer;

public class FindByNameItem extends BaseAction implements UserAction {
    
    public FindByNameItem(int menuNum, String menuInfo, Consumer<String> output) {
        super(menuNum, menuInfo, output);
    }
    
    @Override
    public void execute(Input input, ITracker tracker) {
        output.accept("------------ Поиск заявок по имени ----------------");
        String name = input.ask("Введите имя заявки: ");
        List<Item> items = tracker.findItemsByName(name);
        if (items.size() > 0) {
            output.accept(" Заявки найдены: ");
            for (Item item : items) {
                output.accept(item.toString());
            }
        } else {
            output.accept(" Заявки с таким именем не найдены ");
        }
        output.accept("------------ Конец --------------------------------");
    }
}
