package inputOutput;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    private Config config;

    @Before
    public void setUp() {

        // так не работает
        // файл app.properties в папке resources
        // config = new Config("chapter_006_input_output/target/classes/app.properties");
        // или
        // config = new Config("chapter_006_input_output/src/main/resources/app.properties");

        // так тоже  не работает
        // файл appForTest.properties в папке resources которая в папке test
        // config = new Config("chapter_006_input_output/target/test-classes/appForTest.properties");

        // вот так работает
        String prop = System.getProperty("user.dir"); // получаем путь до корня текущего МОДУЛЯ
        String path = new File(prop).getPath() + "/src/main/resources/app.properties";
        config = new Config(path);
    }

    @Test
    public void loadTest1() {
        config.load();
        assertThat(config.getValues().get("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void loadTest2() {
        config.load();
        assertThat(config.getValues().get("## PostgreSQL"), is(nullValue()));
    }

    @Test
    public void valueTest1() {
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void valueTest2() {
        config.load();
        assertThat(config.value("## PostgreSQL"), is(nullValue()));
    }

    @Test
    public void testToString() {
        final PrintStream stdout = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        // меняем стандартный вывод
        System.setOut(new PrintStream(out));

        // вызываем то что будем проверять
        System.out.println(config);

        // проверяем
        String nextLine = System.lineSeparator();
        assertThat(
            new String(out.toByteArray()),
            is(
                new StringBuilder()
                    .append("## PostgreSQL=test").append(nextLine)
                    .append(nextLine)
                    .append("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect").append(nextLine)
                    .append("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio").append(nextLine)
                    .append("hibernate.connection.driver_class=org.postgresql.Driver").append(nextLine)
                    .append("hibernate.connection.username=postgres").append(nextLine)
                    .append("hibernate.connection.password=password").append(nextLine)
                    .append(nextLine)
                    .append("testtring").append(nextLine)
                    .toString()
            )
        );

        // меняем вывод обратно
        System.setOut(stdout);
    }
}