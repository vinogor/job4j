package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class FindByIdItem extends BaseAction implements UserAction {
    
    public FindByIdItem(int menuNum, String menuInfo, Consumer<String> output) {
        super(menuNum, menuInfo, output);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        output.accept("------------ Поиск заявки по id -------------------");
        String id = input.ask("Введите id заявки: ");
        Item result = tracker.findItemById(id);
        if (result != null) {
            output.accept(" Заявка найдена: " + result);
        } else {
            output.accept(" Заявок с таким id не найдено");
        }
        output.accept("------------ Конец --------------------------------");
    }
}
