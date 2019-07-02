package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class UpdateItem extends BaseAction implements UserAction {
    
    public UpdateItem(int menuNum, String menuInfo, Consumer<String> output) {
        super(menuNum, menuInfo, output);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        output.accept("------------ Изменение имеющейся заявки -----------");
        String id = input.ask("Введите id заявки: ");
        String name = input.ask("Введите новое имя заявки: ");
        String desc = input.ask("Введите новое описание заявки: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        boolean result = tracker.replace(id, item);
        if (result) {
            output.accept(" Заявка обновлена ");
        } else {
            output.accept(" Заявки с таким id не найдено ");
        }
        output.accept("------------ Конец --------------------------------");
    }
}