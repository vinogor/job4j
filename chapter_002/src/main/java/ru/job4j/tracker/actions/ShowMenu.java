package ru.job4j.tracker.actions;

import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class ShowMenu extends BaseAction implements UserAction {

    private final MenuTracker menuTracker;
    
    public ShowMenu(int menuNum, String menuInfo, MenuTracker menuTracker) {
        super(menuNum, menuInfo);
        this.menuTracker = menuTracker;
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        menuTracker.show();
    }
}