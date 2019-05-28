package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Array-based CRUD storage for items.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 29.05.2019
 */
public class Tracker {

	private Item[] items = new Item[100];
	private int position = 0;
	private static final Random RND = new Random();

	/**
	 * Adds item to storage and assign to him generated pseudo-unique id.
	 *
	 * @param item item to add.
	 * @return the same item with generated pseudo-unique id.
	 */
	public Item add(Item item) {
		item.setId(generateId());
		this.items[position] = item;
		this.position++;
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
		for (int i = 0; i < position; i++) {
			if (this.items[i].getId().equals(id)) {
				result = this.items[i];
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
	public Item[] findAll() {
		return Arrays.copyOf(items, position);
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
			System.arraycopy(this.items, index + 1, this.items, index, position - index);
			this.position--;
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
			this.items[index] = item;
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
	public Item[] findItemsByName(String name) {
		Item[] itemsCopy = new Item[100];
		int counter = 0;
		for (int i = 0; i < position; i++) {
			if (this.items[i].getName().equals(name)) {
				itemsCopy[counter] = this.items[i];
				counter++;
			}
		}
		return Arrays.copyOf(itemsCopy, counter);
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
		for (int i = 0; i < position; i++) {
			if (this.items[i].getId().equals(id)) {
				result = i;
				break;
			}
		}
		return result;
	}
}