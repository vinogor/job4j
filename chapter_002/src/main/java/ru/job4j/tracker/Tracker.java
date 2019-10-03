package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Array-based CRUD storage for items.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 29.05.2019
 */
public class Tracker implements ITracker {

    private List<Item> items = new ArrayList<>();
    private static final Random RND = new Random();

    /**
     * Adds item to storage and assign to him generated pseudo-unique id.
     *
     * @param item item to add.
     * @return the same item with generated pseudo-unique id.
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Searches for an element with the given id.
     *
     * @param id pseudo-unique identifier of item.
     * @return item if search success, else return null.
     */
    public Item findItemById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Return copy of storage without null elements.
     *
     * @return copy of array "items" without null elements.
     */
    public ArrayList<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Remove item with the given id.
     *
     * @param id pseudo-unique identifier of item.
     * @return true if success, false if not.
     */
    public boolean delete(String id) {
        boolean result = false;

        int index = findIndexById(id);

        if (index != -1) {
            items.remove(index);
            result = true;
        }
        return result;
    }

    /**
     * Item in storage with given id is replaced with given item.
     * Given item is set given id.
     *
     * @param id   pseudo-unique identifier of item.
     * @param item item to replace.
     * @return true if search success, false if not.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            item.setId(id);
            this.items.set(index, item);
            result = true;
        }
        return result;
    }

    /**
     * Searches for an elements with the given name.
     *
     * @param name to search.
     * @return array of items with given name.
     */
    public List<Item> findItemsByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Generate pseudo-unique value.
     *
     * @return pseudo-unique value.
     */
    private String generateId() {
        return String.valueOf(RND.nextInt() + System.currentTimeMillis());
    }

    /**
     * Searches index of item with the given id.
     *
     * @param id pseudo-unique identifier of item.
     * @return index if search success, -1 if not.
     */
    private int findIndexById(String id) {
        int result = -1;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}