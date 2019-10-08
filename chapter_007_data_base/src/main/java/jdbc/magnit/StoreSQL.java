package jdbc.magnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void connectDB() {
        String url = config.get("url");
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("используем драйвер \"" + meta.getDriverName() + "\"");
                System.out.println("connection получен");
                this.connection = conn;
            }
        } catch (SQLException e) {
            System.out.println("ОШИБКА " + e.getMessage());
        }
    }

    public void createTable() {
        try (PreparedStatement ps = connection.prepareStatement("" +
            "CREATE TABLE IF NOT EXISTS entries(   " +
            "    field   INTEGER                  " +
            ");"
        )) {
            ps.execute();
        } catch (SQLException e) {
            System.out.println("    ошибка при попытке создать таблицу или проверить её существование");
            e.printStackTrace();
        }
    }

    public void generate(int size) {

        // очищаем таблицу
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM entries")) {
            ps.execute();
            System.out.println("очистили таблицу от старого");
        } catch (Exception e) {
            System.out.println("ошибка DELETE FROM");
            e.printStackTrace();
        }

        // почему то идея не видит таблицу entries БД sqlite, хотя в блок Database я базу подключил
        // решил - открепил старый Postgres от идеи и sqlite заработал
        String sql = "INSERT INTO entries (field) VALUES(?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (int i = 1; i < size + 1; i++) {
                ps.setInt(1, i);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            System.out.println("заполнили таблицу ");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("    ошибка отката изменений");
                ex.printStackTrace();
            }
            System.out.println("    ошибка при попытке добавить");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("    ошибка включения автокоммита");
                e.printStackTrace();
            }
        }
    }

    public List<Entry> load() {
        String sql = "SELECT * FROM entries";
        List<Entry> list = new ArrayList<>();
        try (
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            System.out.println("выгружаем данные из таблицы");
            while (rs.next()) {
                list.add(new Entry(
                    rs.getInt("field")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}