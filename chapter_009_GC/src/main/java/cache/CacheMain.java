package cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

// Создать структуру данных типа кеш. Кеш должен быть абстрактный. То есть необходимо,
// что бы можно было задать ключ получения объекта кеша и в случае если его нет в памяти,
// задать поведение загрузки этого объекта в кеш.
//
// Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы
// из системы и выдавать текст при запросе имени файла. Если в кеше файла нет.
// Кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
// Текстовые файл должны лежать в одной директории.
//
// Пример. Names.txt, Address.txt - файлы в системе.
// При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.

public class CacheMain {

    private static final Logger LOG = LogManager.getLogger(CacheMain.class.getName());
    private static final String NEXT_LINE = System.lineSeparator();
    private SoftCache<String, String> cache = new SoftCache<>();

    public static void main(String[] args) {
        new CacheMain().start();
    }

    private void start() {
        getTextByFileName("Names.txt");
        getTextByFileName("Address.txt");
        getTextByFileName("Names.txt");
        getTextByFileName("Address.txt");
    }

    private String getTextByFileName(String fileName) {
        LOG.trace("ищем в кэшэ по ключу " + fileName);
        String cacheText = cache.get(fileName);
        if (cacheText == null) {
            LOG.trace("в кэше ничего не нашлось, начинаем чтение файла " + fileName);
            String text = readFile(fileName);
            LOG.trace("записываем в кэш данные по ключу " + fileName);
            cache.put(fileName, text);
            return text;
        }
        LOG.trace("запись в кэшэ по ключу " + fileName + " найдена: ");
        LOG.trace(NEXT_LINE + cacheText);
        return cacheText;
    }

    private String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        String path = CacheMain.class.getClassLoader().getResource("").getFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + fileName))) {
            String line = reader.readLine();

            while (line != null) {
                LOG.trace("    строка: " + line);
                sb.append(line).append(NEXT_LINE);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}