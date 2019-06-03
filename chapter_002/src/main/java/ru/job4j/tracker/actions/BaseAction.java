package ru.job4j.tracker.actions;

public abstract class BaseAction implements UserAction {
    private final int menuNum;
    private final String menuInfo;
    
    public BaseAction(final int menuNum, final String menuInfo) {
        this.menuNum = menuNum;
        this.menuInfo = menuInfo;
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