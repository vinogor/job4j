package inputOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 1. Создать программу для поиска файла.
// 2. Программа должна искать данные в заданном каталоге и подкаталогах.
// 3. Имя файла может задаваться целиком, по маске, по регулярному выражение(не обязательно).
// 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt

// Ключи
// -d - директория в которая начинать поиск.
// -n - имя файл, маска, либо регулярное выражение.
// -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.
// -o - результат записать в файл.

// 5. Программа должна записывать результат в файл.
// 6. В программе должна быть валидация ключей и подсказка.

// для тестов
//
// Program arguments:
// -d c:\Users\AndUser\Downloads\ -n *.txt -m -o log.txt
// -d c:\Users\AndUser\Downloads\ -n fileFoeSearch.txt -f -o log.txt

public class Search2 {

    private String directory;
    private String name;
    private String output;
    private String typeOfSearch;

    public Search2(Args2 arguments) throws Exception {
        this.directory = arguments.getDir();
        this.name = arguments.getName();
        this.output = arguments.getOutput();
        this.typeOfSearch = arguments.getTypeOfSearch();
    }

    private void start() {
        Search search = new Search();
        List<File> files = null;

        switch (typeOfSearch) {
            case "full":
                files = search.files(directory, new CustomFileFilter(name));
                break;
            case "mask":
                // пока только для любого имени с конкретным расширением
                String ext = name.substring(name.lastIndexOf(".") + 1);
                List<String> exts = new ArrayList<>();
                exts.add(ext);
                files = search.files(directory, new CustomFileFilter(exts, true));
                break;
            case "regular":
                // однажды сделать
                break;
        }
        writeFiles(directory, output, files);
    }

    private void writeFiles(String directory, String output, List<File> files) {
        File outputFile = new File(directory, output);
        System.out.println("outputFile: " + outputFile);

        try (FileWriter fw = new FileWriter(outputFile)) {
            for (File file : files) {
                fw.write(file.getAbsolutePath() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Args2 arguments = new Args2(args);
        new Search2(arguments).start();
    }
}
