package inputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<String> answers = new ArrayList<>();

    private static final String YOU = "You: ";
    private static final String END = "закончить";
    private static final String STOP = "стоп";
    private static final String AUTO_RESPONDER_OFF = "=== автоответчик отключён ===";
    private static final String AUTO_RESPONDER_ON = "=== автоответчик включен ===";
    private static final String CONTINUE = "продолжить";
    private static final String BOT = "Bot: ";
    private static final String END_WORK = "Завершаем работу";
    private static final String FILE_NAME = "chat.txt";

    private void start() throws IOException {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        readAnswers(); // читаем весь файл и заполняем построчно List

        System.out.print(YOU);
        String input = br.readLine();
        boolean shouldAnswer = true;
        while (!END.equals(input)) {

            if (STOP.equals(input) && shouldAnswer) {
                shouldAnswer = false;
                System.out.println(AUTO_RESPONDER_OFF);
            } else if (input.equals(CONTINUE) && !shouldAnswer) {
                shouldAnswer = true;
                System.out.println(AUTO_RESPONDER_ON);
            } else if (shouldAnswer) {
                System.out.println(BOT + answer());
            }
            System.out.print(YOU);
            input = br.readLine();
        }
        System.out.println(END_WORK);
    }

    private void readAnswers() {
        try (
            BufferedReader reader =
                new BufferedReader(
                    new FileReader(
                        Analise.class.getClassLoader().getResource("").getFile() + FILE_NAME
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