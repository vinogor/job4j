package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    Item add(Item item);

    //    void replace(String id, Item item);
    boolean replace(String id, Item item);

    //    void delete(String id);
    boolean delete(String id);

    //    Item[] findAll();
    List<Item> findAll();

    //    Item[] findItemsByName(String key);
    List<Item> findItemsByName(String key);

    Item findItemById(String id);
}
