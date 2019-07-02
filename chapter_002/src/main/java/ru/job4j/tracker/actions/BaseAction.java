package ru.job4j.tracker.actions;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {
    private final int menuNum;
    private final String menuInfo;
    protected final Consumer<String> output;
    
    public BaseAction(final int menuNum, final String menuInfo, Consumer<String> output) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
        this.output = output;
    }
    
    @Override
    public int key() {
        return this.menuNum;
    }
    
    @Override
    public String info() {
        return this.menuInfo;
    }
}