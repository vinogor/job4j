package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;

public class FindAllItem extends BaseAction implements UserAction {
    
    public FindAllItem(int menuNum, String menuInfo) {
        super(menuNum, menuInfo);
    }
    
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Вывод содержимого хранилища ----------");
//        Item[] items = tracker.findAll();
        ArrayList<Item> items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("------------ Конец --------------------------------");
    }
}