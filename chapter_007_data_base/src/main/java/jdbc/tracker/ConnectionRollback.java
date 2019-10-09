package jdbc.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection, which rollback all commits.
 * It is used for integration test.
 */

//  сделаем фабричный метод, который создает Connection,
//  в котором метод close работает с вызовом rollback.
//  Всё это работает через Proxy
//  В итоге можно работать с БД, которая сохраняет только в оперативу, а не на жёсткий

// хорошая статьи по теме
// https://restless-man.livejournal.com/24320.html
// + Хорстманн 1 том, 6.5. Прокси-классы

public class ConnectionRollback {

    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(

            // класс-лоадер - зачеееем?
            ConnectionRollback.class.getClassLoader(),

            // массив с интерфейсов, которые использует класс, прокси-объект которого будем создавать
            // методы этих интерфейсов будут перехватываться InvocationHandler-ом
            new Class[] { Connection.class },

            // ниже должен быть объект InvocationHandler - он перехватывает
            // все вызовы методов proxy-объекта
            // Его главный метод Object invoke() - срабатывает при вызове метода

            // method - вызванный метод
            // args - аргументы метода
            // proxy - сам прокси объект (где пригодится???)
            //     вызов метода ориг объекта - method.invoke(исходный объект, args);
            //     оригинальный объект надо передать через конструктор в кастомный ИнвакХэндлер
            (proxy, method, args) -> {
                Object rsl = null;
                if ("close".equals(method.getName())) {
                    connection.rollback();
                    connection.close();
                } else {
                    rsl = method.invoke(connection, args);
                }
                return rsl;
            }
        );
    }
}