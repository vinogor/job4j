package ru.job4j.tracker;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerTest {

    private static final String FAKEID = "fakeId";
    private static final String FAKENAME = "fakeName";
    private static final String TESTNAME1 = "test1";
    private static final String TESTNAME2 = "test2";
    private static final String TESTNAME3 = "test3";

    private Tracker tracker;
    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void setUp() {
        tracker = new Tracker();
        item1 = new Item(TESTNAME1, "testDescription1", 1L);
        item2 = new Item(TESTNAME2, "testDescription2", 2L);
        item3 = new Item(TESTNAME3, "testDescription3", 3L);
    }

    @Test
    public void add() {
        Item result = tracker.add(item1);
        assertThat(result.getId(), is(item1.getId()));
    }

    @Test
    public void findItemById1() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Item result = tracker.findItemById(item2.getId());
        assertThat(result, is(item2));
    }

    @Test
    public void findItemById2() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Item result = tracker.findItemById(FAKEID);
        assertThat(result, is(IsNull.nullValue()));
    }

    @Test
    public void findAll1() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Item[] result = tracker.findAll();
        assertThat(result, is(new Item[]{item1, item2, item3}));
    }

    @Test
    public void findAll2() {
        Tracker tracker = new Tracker();
        Item[] result = tracker.findAll();
        assertThat(result, is(new Item[0]));
    }

    @Test
    public void delete1() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Boolean result = tracker.delete(item2.getId());
        assertThat(result, is(true));
    }

    @Test
    public void delete2() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Boolean result = tracker.delete(FAKEID);
        assertThat(result, is(false));
    }

    @Test
    public void replace1() {
        tracker.add(item1);
        tracker.replace(item1.getId(), item2);

        assertThat(tracker.findItemById(item1.getId()).getName(), is(TESTNAME2));
    }

    @Test
    public void replace2() {
        tracker.add(item1);

        boolean result = tracker.replace(item1.getId(), item2);
        assertThat(result, is(true));
    }

    @Test
    public void findItemsByName1() {
        item3.setName(TESTNAME1);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Item[] result = tracker.findItemsByName(TESTNAME1);
        assertThat(result, is(new Item[]{item1, item3}));
    }

    @Test
    public void findItemsByName2() {
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Item[] result = tracker.findItemsByName(FAKENAME);
        assertThat(result, is(new Item[0]));
    }
}