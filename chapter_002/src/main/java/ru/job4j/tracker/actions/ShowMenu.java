package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

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