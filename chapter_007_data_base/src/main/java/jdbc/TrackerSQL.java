package jdbc;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

// Реализовать методы интефейса ITracker. Храниться и извлекать все данные нужно из базы данных.
// Предусмотреть возможность, что структуры в базе еще нет. И вам нужно ее создать при старте.
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection = null;

    // получаем логин пароль ссылку на БД
    // подключаемся к БД, получаем коннект
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
            );
            // если с таким именем не существует - создаём
            try (PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS tracker.public.items(" +
                    "id      SERIAL PRIMARY KEY," +
                    "name         VARCHAR(30)," +
                    "describe     TEXT," +
                    "time         TIMESTAMP" +
                    ");"
            )) {
                ps.execute();
            } catch (SQLException e) {
                System.out.println("    ошибка при попытке создать таблицу или проверить её существование");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        String sql =
            "INSERT INTO tracker.public.items(name, describe, time) " +
                "VALUES(?, ?, ?); ";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDecs());
            ps.setTimestamp(3, new Timestamp(item.getTime()));
            ps.executeUpdate();
            System.out.println("    строка добавлена");

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int key = generatedKeys.getInt(1);
                    System.out.println("    id добавленной записи: " + key);
                    item.setId(String.valueOf(key));
                    return item;
                }
            } catch (SQLException e) {
                System.out.println("    ошибка при попытке получить сгенерированный ключ");
            }
        } catch (SQLException e) {
            System.out.println("    ошибка при попытке добавить");
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String sql =
            "DELETE FROM tracker.public.items WHERE id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            int delRows = ps.executeUpdate();
            if (delRows == 1) {
                System.out.println("    строка удалена");
                return true;
            }
            System.out.println("    данного id в базе не найдено");
        } catch (SQLException e) {
            System.out.println("    ошибка удаления");
        }
        return false;
    }

    @Override
    public boolean replace(String id, Item item) {
        String sql =
            "UPDATE tracker.public.items SET name = ?, describe = ?, time = ? WHERE id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDecs());
            ps.setTimestamp(3, new Timestamp(item.getTime()));
            ps.setInt(4, Integer.parseInt(id));

            int updRows = ps.executeUpdate();
            if (updRows == 1) {
                System.out.println("    item обновлён");
                return true;
            }
            System.out.println("    данного id в базе не найдено");

        } catch (SQLException e) {
            System.out.println("    ошибка изменения");
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        String sql =
            "SELECT * FROM tracker.public.items ";
        try (
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            List<Item> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Item(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    rs.getString("describe"),
                    rs.getTimestamp("time").getTime()
                ));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("    ошибка получения записей таблицы");
        }
        return null;
    }

    @Override
    public List<Item> findItemsByName(String key) {
        String sql =
            "SELECT * FROM tracker.public.items WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, key);
            try (ResultSet rs = ps.executeQuery();) {
                List<Item> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Item(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getTimestamp("time").getTime()
                    ));
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("    ошибка получения записей таблицы");
        }
        return null;
    }

    @Override
    public Item findItemById(String id) {
        String sql =
            "SELECT * FROM tracker.public.items WHERE id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("    item найден");
                    return new Item(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        rs.getString("describe"),
                        rs.getTimestamp("time").getTime()
                    );
                } else {
                    System.out.println("    item НЕ найден");
                }
            }
        } catch (SQLException e) {
            System.out.println("    ошибка поиска");
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        TrackerSQL trackerSQL = new TrackerSQL();
        trackerSQL.init();
//        trackerSQL.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
//        trackerSQL.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
//        trackerSQL.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
//        trackerSQL.add(new Item("test name1", "test desc1", System.currentTimeMillis()));

//        trackerSQL.delete("1572089676094");

//        trackerSQL.findItemById("1571135885277");

//        trackerSQL.replace("1570903021782", new Item("test name NEW", "test desc1 NEW ", System.currentTimeMillis()));

//        List<Item> all = trackerSQL.findAll();
//        for (Item item : all) {
//            System.out.println(item);
//        }

        List<Item> all = trackerSQL.findItemsByName("test name NEW");
        for (Item item : all) {
            System.out.println(item);
        }
    }
}