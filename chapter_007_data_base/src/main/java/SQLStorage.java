
import java.sql.*;

public class SQLStorage {

    public static void main(String[] args) {

        // формат
        // jdbc:postgresql:database
        // jdbc:postgresql:/
        // jdbc:postgresql://host/database
        // jdbc:postgresql://host/
        // jdbc:postgresql://host:port/database
        // jdbc:postgresql://host:port/

        String url = "jdbc:postgresql://localhost:5432/job4j"; // урл для коннекта
        String user = "postgres";
        String pass = "1234";
        Connection conn = null;
        try {
            // вызываем статическую фабрику
            conn = DriverManager.getConnection(url, user, pass);


            // ======== запрос - добавляем/удаляем данные в БД - выводим кол-во изменений

            PreparedStatement st = conn.prepareStatement("" +
                    "DELETE FROM job4j.filters.product WHERE id = ?"

//                    "UPDATE job4j.filters.product SET name = ?  WHERE id < ?"

//                  "INSERT INTO job4j.filters.product(name, type_id, expired_date, quantity)" +
//                  "VALUES (?, ?, ?, ?)",
                // для того чтобы возвращался первичный ключ добавленной записи
//                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, 1);

//            st.setString(1, "newName123");
//            st.setInt(2, 3);

//            st.setString(1, "newName");
//            st.setInt(2, 1);
//            st.setDate(3, new java.sql.Date(new Date().getTime()));
//
//            // как задать тип money ???????
//            // st.setFloat(4, 14);
//            // st.setString(4, "78,13");
//            st.setInt(4, 666);

            // выполняем SQL запрос, сохраняем кол-во добавленных/изменённых/удалённых строк?
            int rowsAdd = st.executeUpdate();
            System.out.println("добавлено/изменено/удалено строк: " + rowsAdd);

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("id добавленной записи: " + generatedKeys.getInt(1));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


/*
            // ======== запрос - получаем результат - приводим к норм виду - выводим на экран

            // создаём объект через который будет отправлять SQL команды
            // Statement st = conn.createStatement();
            PreparedStatement st = conn.prepareStatement(
                    "SELECT * FROM job4j.filters.product p WHERE p.id in (?, ?, ?)"
            );
            // индексация с ЕДЕНИЦЫ!
            st.setInt(1, 1);
            st.setInt(2, 3);
            st.setInt(3, 5);

            // выполняем SQL запрос и сохраняем результат
            // rs - это не коллекция, а что-то типо итератора!!! по набору записей результата
            ResultSet rs = st.executeQuery(
            );
            while (rs.next()) {
                // получить по индексу
                // System.out.println(rs.getString(1));

                // но лучше по имени колонки
                System.out.println(String.format("%s %s",
                        rs.getString("name"),
                        rs.getDate("expired_date")
                ));
            }
            rs.close();
            st.close();
*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
