package inputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chat {

    private List<String> answers = new ArrayList<>();

    private void start() throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        readAnswers(); // читаем весь файл и заполняем построчно List

        System.out.print("You: ");
        String input = br.readLine();
        boolean shouldAnswer = true;
        while (!input.equals("закончить")) {

            if (input.equals("стоп") && shouldAnswer) {
                shouldAnswer = false;
                System.out.println("=== автоответчик отключён ===");
            } else if (input.equals("продолжить") && !shouldAnswer) {
                shouldAnswer = true;
                System.out.println("=== автоответчик включен ===");
            } else if (shouldAnswer) {
                System.out.println("Bot: " + answer());
            }
            System.out.print("You: ");
            input = br.readLine();
        }
        System.out.println("Завершаем работу");
    }

    private void readAnswers() {
        try (
            BufferedReader reader =
                new BufferedReader(
                    new FileReader(
                        Analise.class.getClassLoader().getResource("").getFile() + "chat.txt"
                    )
                )
        ) {
            reader.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // программа берет случайную фразу из текстового файла и выводит в ответ
    private String answer() {
        int randomIndex = (int) (Math.random() * answers.size());
        return answers.get(randomIndex);
    }

    public static void main(String[] args) {
        try {
            new Chat().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}