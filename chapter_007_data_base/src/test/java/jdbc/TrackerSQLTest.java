package jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private TrackerSQL sql;

    private final static String NAME_1 = "name_1";
    private final static String NAME_2 = "name_2";
    private final static String NAME_3 = "name_3";

    private final static String DESC_1 = "desc_1";
    private final static String DESC_2 = "desc_2";
    private final static String DESC_3 = "desc_3";
    
    private final static long TIME_1 = 1570110453726L;
    private final static long TIME_2 = 1570110453826L;
    private final static long TIME_3 = 1570110453926L;
    
    @Before
    public void setUp() {
        sql = new TrackerSQL();
    }

    @Test
    public void checkConnection() {
        assertThat(sql.init(), is(true));
    }

    @Test
    public void addTest01() {
        sql.init();
        truncateTable();
        Item item = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        assertThat(item, is(new Item(item.getId(), NAME_1, DESC_1, TIME_1)));
    }

    @Test
    public void deleteTest01() {
        sql.init();
        truncateTable();
        sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        sql.add(new Item(NAME_3, DESC_3, TIME_3));
        assertThat(sql.delete(item.getId()), is(true));
    }

    @Test
    public void deleteTest02() {
        sql.init();
        truncateTable();
        sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        assertThat(sql.delete(item.getId()+1), is(false));
    }

    @Test
    public void replaceTest01() {
        sql.init();
        truncateTable();
        Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        boolean result = sql.replace(item1.getId(), item2);
        assertThat(result, is(true));
    }

    @Test
    public void replaceTest02() {
        sql.init();
        truncateTable();
        Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        boolean result = sql.replace(item2.getId() + 1, item1);
        assertThat(result, is(false));
    }

    @Test
    public void findAllTest01() {
        sql.init();
        truncateTable();
        Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        List<Item> result = sql.findAll();
        List<Item> expect = new ArrayList<>();
        expect.add(item1);
        expect.add(item2);
        assertThat(result, is(expect));
    }

    @Test
    public void findItemsByNameTest01() {
        sql.init();
        truncateTable();
        Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        sql.add(new Item(NAME_2, DESC_2, TIME_2));
        List<Item> result = sql.findItemsByName(NAME_1);
        List<Item> expect = new ArrayList<>();
        expect.add(item1);
        assertThat(result, is(expect));
    }

    @Test
    public void findItemsByNameTest2() {
        sql.init();
        truncateTable();
        Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item2 = sql.add(new Item(NAME_1, DESC_2, TIME_2));
        List<Item> result = sql.findItemsByName(NAME_1);
        List<Item> expect = new ArrayList<>();
        expect.add(item1);
        expect.add(item2);
        assertThat(result, is(expect));
    }

    @Test
    public void findItemsByNameTest3() {
        sql.init();
        truncateTable();
        sql.add(new Item(NAME_1, DESC_1, TIME_1));
        sql.add(new Item(NAME_1, DESC_2, TIME_2));
        List<Item> result = sql.findItemsByName(NAME_2);
        List<Item> expect = new ArrayList<>();
        assertThat(result, is(expect));
    }

    @Test
    public void findItemByIdTest01() {
        sql.init();
        truncateTable();
        Item item = sql.add(new Item(NAME_1, DESC_1, TIME_1));
        sql.add(new Item(NAME_2, DESC_2, TIME_2));
        Item result = sql.findItemById(item.getId());
        assertThat(result, is(item));
    }

    @Test
    public void findItemByIdTest02() {
        sql.init();
        truncateTable();
        sql.add(new Item(NAME_1, DESC_1, TIME_1));
        Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
        Item result = sql.findItemById(item.getId() + 1);
        assertThat(result, is(nullValue()));
    }

    @After
    public void tearDown() {
        truncateTable();
    }

    private void truncateTable() {
        Connection connection = sql.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE tracker.public.items")) {
            ps.execute();
        } catch (Exception e) {
            System.out.println("ошибка DROP TABLE");
            e.printStackTrace();
        }
    }
}