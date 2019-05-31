package ru.job4j.tracker.menuElements;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

public class ShowMenu implements UserAction {

    private int menuNum;
    private String menuInfo;

    public ShowMenu(int menuNum, String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
    }

    @Override
    public int key() {
        return menuNum;
    }

    @Override
    public void execute(Input input, Tracker tracker) {

    }

    @Override
    public String info() {
        return menuInfo;
    }
}