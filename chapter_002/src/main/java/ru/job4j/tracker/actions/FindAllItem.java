package ru.job4j.tracker.actions;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FindAllItem extends BaseAction implements UserAction {
    
    public FindAllItem(int menuNum, String menuInfo, Consumer<String> output) {
        super(menuNum, menuInfo, output);
    }
    
    @Override
    public void execute(Input input, ITracker tracker) {
        output.accept("------------ Вывод содержимого хранилища ----------");
//        Item[] items = tracker.findAll();
        List<Item> items = tracker.findAll();
        for (Item item : items) {
//            System.out.println(item);
            output.accept(item.toString());
        }
        output.accept("------------ Конец --------------------------------");
    }
}