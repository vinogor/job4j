package generator;

import java.util.Map;

public interface Generator {
    String generate(String template, Map<String, String> map);
}