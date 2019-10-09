package jdbc;

import jdbc.tracker.ConnectionRollback;
import jdbc.tracker.TrackerSQL;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private final static String NAME_1 = "name_1";
    private final static String NAME_2 = "name_2";
    private final static String NAME_3 = "name_3";

    private final static String DESC_1 = "desc_1";
    private final static String DESC_2 = "desc_2";
    private final static String DESC_3 = "desc_3";
    
    private final static long TIME_1 = 1570110453726L;
    private final static long TIME_2 = 1570110453826L;
    private final static long TIME_3 = 1570110453926L;

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void addTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            assertThat(item, is(new Item(item.getId(), NAME_1, DESC_1, TIME_1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            sql.add(new Item(NAME_3, DESC_3, TIME_3));
            assertThat(sql.delete(item.getId()), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest02() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            assertThat(sql.delete(item.getId()+1), is(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            boolean result = sql.replace(item1.getId(), item2);
            assertThat(result, is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceTest02() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            boolean result = sql.replace(item2.getId() + 1, item1);
            assertThat(result, is(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item2 = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            List<Item> result = sql.findAll();
            List<Item> expect = new ArrayList<>();
            expect.add(item1);
            expect.add(item2);
            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findItemsByNameTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            sql.add(new Item(NAME_2, DESC_2, TIME_2));
            List<Item> result = sql.findItemsByName(NAME_1);
            List<Item> expect = new ArrayList<>();
            expect.add(item1);
            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findItemsByNameTest2() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item2 = sql.add(new Item(NAME_1, DESC_2, TIME_2));
            List<Item> result = sql.findItemsByName(NAME_1);
            List<Item> expect = new ArrayList<>();
            expect.add(item1);
            expect.add(item2);
            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findItemsByNameTest3() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            sql.add(new Item(NAME_1, DESC_1, TIME_1));
            sql.add(new Item(NAME_1, DESC_2, TIME_2));
            List<Item> result = sql.findItemsByName(NAME_2);
            List<Item> expect = new ArrayList<>();
            assertThat(result, is(expect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findItemByIdTest01() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = sql.add(new Item(NAME_1, DESC_1, TIME_1));
            sql.add(new Item(NAME_2, DESC_2, TIME_2));
            Item result = sql.findItemById(item.getId());
            assertThat(result, is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findItemByIdTest02() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            sql.add(new Item(NAME_1, DESC_1, TIME_1));
            Item item = sql.add(new Item(NAME_2, DESC_2, TIME_2));
            Item result = sql.findItemById(item.getId() + 1);
            assertThat(result, is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}