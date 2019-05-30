package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void addNewItemTest1() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "test name1", "test desc1", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name1"));
    }

    @Test
    public void editItemTest1() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"3", item.getId(), "test replace", "заменили заявку", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findItemById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void delItemTest1() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"4", item.getId(), "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }
}