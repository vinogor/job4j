package inputOutput;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    private Config config;

    @Before
    public void setUp() {
//        String path = "chapter_006_input_output/src/main/java/inputOutput/app.properties";
        config = new Config(
//            "app.properties"
//            "src/main/java/inputOutput/app.properties"
//            "c:\\Projects\\IdeaProjects\\job4j\\chapter_006_input_output\\src\\main\\java\\inputOutput\\app.properties"
        );
    }

    @Test
    public void load() {
        config.load();
        assertThat(config.getValues().get("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void value() {
    }

    @Test
    public void testToString() {
    }
}