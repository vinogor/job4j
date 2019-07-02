package ru.job4j.tracker.actions;

import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class ExitProgram extends BaseAction implements UserAction {
    
    private final StartUI ui;
    
    public ExitProgram(int menuNum, String menuInfo, StartUI ui, Consumer<String> output) {
        super(menuNum, menuInfo, output);
        this.ui = ui;
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        output.accept("Завершаем работу программы.");
        this.ui.stop();
    }
}