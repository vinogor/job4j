package inputOutput;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

// 1. Реализуйте метод load по аналогии с методом toString. Метод load должен загружать пару ключ-значение в Map values.
// 2. Реализуйте метод value(String key) он должен возвращать значение ключа.
// 3. Напишите тест ConfigTest.

// Примечания. Для работы с потоками ввода-вывода нужно использовать конструкцию try-with-resources.

// Метод load - должен считать все ключи в карту values.
// Важно: в файле могут быть пустые строки и комментарии их нужно пропускать.

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config() {
        this.path = "app.properties";
    }

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read
                .lines() // получаем поток строк
                .filter(x -> !(x.isEmpty()) && !(x.isBlank()) && !(x.substring(0, 2)).equals("##")) // убираем пустые и комменты
                .map(x -> x.split("=")) // выделяем будущий ключ и значение
                .forEachOrdered(x -> values.put(x[0], x[1])); // заполняем мапу
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        // сюда будем собирать строки, разделитель = перенос строки
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            // lines() - в потоке читаем построчно ВСЕ строки, forEach - построчно добавляем в StringJoiner
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString(); // превращаем StringJoiner в обычную строку
    }

    public Map<String, String> getValues() {
        return values;
    }

    public static void main(String[] args) {
        // путь указывать от текущего МОДУЛЯ! (не проекта и не текущей папки)
//        String path = "chapter_006_input_output/src/main/java/inputOutput/app.properties";
//        String property = System.getProperty("user.dir");
//        System.out.println(property);

//        path = property + "/app.properties";
//        Config config = new Config(path);
        Config config = new Config("app.properties");
//        Config config = new Config("c:\\Projects\\IdeaProjects\\job4j\\chapter_006_input_output\\src\\main\\java\\inputOutput\\app.properties");
        System.out.println(config);
        System.out.println("=============");

        config.load();
        Map<String, String> values = config.getValues();
        for (Map.Entry entry : values.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}