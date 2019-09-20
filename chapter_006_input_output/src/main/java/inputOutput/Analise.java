package inputOutput;

import java.io.*;

public class Analise {

    public void unavailable(String source, String target) {

        // source - имя файла лога
        // target - имя файла после анализа.

        // Метод unavailable должен находить диапазоны, когда сервер не работал.
        // Сервер не работал. Если status = 400 или 500.
        // Диапазон считается последовательность статусов 400 и 500

        // Результат анализа нужно записать в файл target.
        // Формат файла: начала периода;конец периода;

        StringBuilder sb = new StringBuilder();

        try (
            BufferedReader reader = new BufferedReader(new FileReader(source));
        ) {
            boolean isUnavailable = false;
            String line = reader.readLine();
            String nextLine = System.lineSeparator();
            while (line != null) {

                if (!isUnavailable && (line.startsWith("400") || line.startsWith("500"))) {
                    isUnavailable = true;
                    sb.append(line.substring(4)).append(";");
                }

                if (isUnavailable && (!line.startsWith("400") && !line.startsWith("500"))) {
                    isUnavailable = false;
                    sb.append(line.substring(4)).append(";").append(nextLine);
                }
                line = reader.readLine();
            }

            if (isUnavailable) {
                sb.append("no data;");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        write(target, sb);
    }

    private void write(String target, StringBuilder sb) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.print(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analise analise = new Analise();
        String path = Analise.class.getClassLoader().getResource("").getFile();
        System.out.println(path);
        analise.unavailable(path + "source.txt", path + "target.txt");
    }
}