package inputOutput;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

//  написать метод, который возвращает список всех файлов с конкретным расширением.

// String parent - это путь до каталога, с которого нужно осуществлять поиск.
// List<String> exts - это расширения файлов, которые мы хотим получить.
// В этой задаче нужно использовать методы.
// File file = new File(path);
// file.listFiles() - возвращает список всех каталогов и файлов находящихся в папке  file.
// file.isDirectory() - проверяет, что файл является директорией.
// file.getName() - возвращает имя файла с расширением.
// В этой задаче нужно написать тесты. Для тестов нужно создать временную структуру с файлами.
// Структуру нужно создавать в папке System.getProperty("java.io.tmpdir")
// Здесь нельзя использовать FileVisitor. Это задание на работу с деревом каталогов.

public class Search {

    public List<File> files(String parent) {
        return files(parent, pathname -> true);
    }

    public List<File> files(String parent, FileFilter filter) {

        File file = new File(parent);
        Queue<File> stack = new LinkedList<>();
        List<File> result = new ArrayList<>();

        if (!file.isDirectory()) {
            return null;
        }
        stack.add(file);

        while (!stack.isEmpty()) {
            File leaf = stack.poll(); // достаём с головы и удаляем

            // получаем список файлов и папок
            File[] files = leaf.listFiles(filter);

            if (files != null) {
                for (File fileTmp : files) {
                    if (fileTmp.isDirectory()) {
                        stack.add(fileTmp);
                    } else {
                        result.add(fileTmp);
                    }
                }
            }
        }
        return result;
    }

    public List<String> convert(List<File> files) {
        return files.stream().map(File::getName).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        // C:\Users\AndUser\AppData\Local\Temp\
        String property = System.getProperty("java.io.tmpdir");
        System.out.println(property);
        Search search = new Search();
        List<String> exts = new ArrayList<>();
        exts.add("txt");
        exts.add("dll");

//        List<String> files = search.convert(search.files(property, new CustomFileFilter(exts, true)));
        List<String> files = search.convert(search.files(property));
        for (String file : files) {
            System.out.println(file);
        }
    }
}