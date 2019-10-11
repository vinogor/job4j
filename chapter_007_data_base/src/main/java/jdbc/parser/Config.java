package jdbc.parser;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final Properties values = new Properties();

    public Config(String props) {
        init(props);
    }

    public void init(String props) {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(props)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}