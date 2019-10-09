package jdbc.tracker;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Реализовать методы интефейса ITracker. Храниться и извлекать все данные нужно из базы данных.
// Предусмотреть возможность, что структуры в базе еще нет. И вам нужно ее создать при старте.
//  jdbc:postgresql://localhost:5432/postgres

public class TrackerSQL implements ITracker, AutoCloseable {

    private final Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    // получаем логин пароль ссылку на БД
    // подключаемся к БД, получаем коннект
/*    public boolean init() {
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
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }*/

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
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("    ошибка при попытке добавить");
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}