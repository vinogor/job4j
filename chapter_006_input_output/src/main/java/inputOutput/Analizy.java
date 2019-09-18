package inputOutput;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {

    public void unavailable(String source, String target) {

        // source - имя файла лога
        // target - имя файла после анализа.

        // Метод anavailable должен находить диапазоны, когда сервер не работал.
        // Сервер не работал. Если status = 400 или 500.
        // Диапазон считается последовательность статусов 400 и 500

        // Результат анализа нужно записать в файл target.
        // Формат файла: начала периода;конец периода;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            boolean isUnavailable = false;
            String line = reader.readLine();
            while (line != null) {

                if (!isUnavailable && (line.startsWith("400") || line.startsWith("500"))) {
                    isUnavailable = true;
                    out.print(line.substring(4) + ";");
                }

                if (isUnavailable && (!line.startsWith("400") && !line.startsWith("500"))) {
                    isUnavailable = false;
                    out.println(line.substring(4) + ";");
                }

                line = reader.readLine();
            }

            if (isUnavailable) {
                out.println("no data;");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        String path = "chapter_006_input_output/src/main/resources/";
        analizy.unavailable(path + "source.txt", path + "target.txt");
    }
}
