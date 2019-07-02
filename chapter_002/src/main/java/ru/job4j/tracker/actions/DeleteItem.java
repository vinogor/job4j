package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction implements UserAction {
    
    public DeleteItem(int menuNum, String menuInfo, Consumer<String> output) {
        super(menuNum, menuInfo, output);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        output.accept("------------ Удаление заявки ----------------------");
        String id = input.ask("Введите id заявки: ");
        boolean result = tracker.delete(id);
        if (result) {
            output.accept(" Заявка удалена ");
        } else {
            output.accept(" Заявки с таким id не найдено ");
        }
        output.accept("------------ Конец --------------------------------");
    }

}
