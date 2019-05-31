package ru.job4j.tracker;

import ru.job4j.tracker.menuElements.*;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения ДЛИНЫ массива меню.
     *
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    public void fillActions() {
        this.actions.add(new ShowMenu(0, "Show menu")); // не испольхуется
        this.actions.add(new AddItem(1, "Add new Item"));
        this.actions.add(new FindAllItem(2, "Show all items"));
        this.actions.add(new UpdateItem(3, "Edit item"));
        this.actions.add(new DeleteItem(4, "Delete item"));
        this.actions.add(new FindByIdItem(5, "Find item by Id"));
        this.actions.add(new FindByNameItem(6, "Find items by name"));
        this.actions.add(new ExitProgram(7, "Exit Program"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.key() + " - " + action.info());
            }
        }
    }
}