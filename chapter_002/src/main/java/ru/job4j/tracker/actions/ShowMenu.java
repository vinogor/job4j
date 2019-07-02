package ru.job4j.tracker.actions;

import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

import java.util.function.Consumer;

public class ShowMenu extends BaseAction implements UserAction {

    private final MenuTracker menuTracker;
    
    public ShowMenu(int menuNum, String menuInfo, MenuTracker menuTracker, Consumer<String> output) {
        super(menuNum, menuInfo, output);
        this.menuTracker = menuTracker;
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        menuTracker.show();
    }
}