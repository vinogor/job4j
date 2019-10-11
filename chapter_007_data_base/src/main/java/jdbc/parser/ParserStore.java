package jdbc.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdbc.parser.MainQuartz.config_parser;
public class ParserStore implements AutoCloseable {

    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(ParserStore.class.getName());

    public void connectDB() {
        try {
            Class.forName(config_parser.get("driver-class-name"));
            this.connection = DriverManager.getConnection(
                config_parser.get("url"),
                config_parser.get("username"),
                config_parser.get("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            LOG.error(e.getStackTrace());
        }
        LOG.trace("получили коннект к БД");
    }

    public void createTable() {
        String sqlCreate = "" +
            "CREATE TABLE IF NOT EXISTS vacancies(       " +
            "    id           SERIAL PRIMARY KEY,        " +
            "    name         VARCHAR(200),              " +
            "    text         TEXT,                      " +
            "    link         TEXT                       " +
            ");                                          ";
        String sqlClean = "" +
            "DELETE FROM parser.public.vacancies         ";

        try (
            PreparedStatement ps1 = connection.prepareStatement(sqlCreate);
            PreparedStatement ps2 = connection.prepareStatement(sqlClean)
        ) {
            ps1.execute();
            LOG.trace("создали таблицу, если она не существовала");
            ps2.execute();
            LOG.trace("очистили таблицу, если она уже существовала");
        } catch (SQLException e) {
            LOG.error(e.getStackTrace());
        }
    }

    public void save(List<Vacancy> vacancies) {

        String sql = "INSERT INTO parser.public.vacancies (name, text, link) VALUES(?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            for (Vacancy vacancy : vacancies) {
                ps.setString(1, vacancy.getName());
                ps.setString(2, vacancy.getText());
                ps.setString(3, vacancy.getLink());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            LOG.trace("заполнили таблицу ");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getStackTrace());
            }
            LOG.error(e.getStackTrace());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getStackTrace());
            }
        }
    }

    public List<Vacancy> load() {
        String sql = "SELECT * FROM parser.public.vacancies";
        List<Vacancy> list = new ArrayList<>();
        try (
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            LOG.trace("выгружаем данные из таблицы");
            while (rs.next()) {
                list.add(new Vacancy(
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("link")
                ));
            }
        } catch (SQLException e) {
            LOG.error(e.getStackTrace());
        }
        return list;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getStackTrace());
            }
        }
    }
}