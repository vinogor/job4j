import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;

public class Attribute {
    public static void main(String[] args) throws IOException {
        Path p0 = Paths.get("c:\\test\\test.txt");

        // формируем представление, оно же набор определённых атрибутов
        BasicFileAttributeView basicView  = Files.getFileAttributeView(p0, BasicFileAttributeView.class);
        // получаем время создания файла в мс
        long fileTime = basicView.readAttributes().creationTime().toMillis();
        // переводим мс с человеко понятную дату
        Date date = new Date(fileTime);
        System.out.println(date);
    }
}
