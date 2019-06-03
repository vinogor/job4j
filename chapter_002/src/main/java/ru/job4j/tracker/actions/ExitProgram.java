package ru.job4j.tracker.actions;

import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class ExitProgram extends BaseAction implements UserAction {
    
    private final StartUI ui;
    
    public ExitProgram(int menuNum, String menuInfo, StartUI ui) {
        super(menuNum, menuInfo);
        this.ui = ui;
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Завершаем работу программы.");
        this.ui.stop();
    }
}