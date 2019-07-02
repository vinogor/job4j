package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.function.Consumer;

public class StubInput implements Input {

    private final Consumer<String> output;
    /**
     * Последовательность ответов пользователя.
     */
    private final String[] value;

    /**
     * Счётчик оличества вызовом метода ask.
     */
    private int position = 0;

    /**
     * Конструктор, инициализирует последовательность ответов пользователя.
     *
     * @param value последовательность ответов пользователя.
     */
    public StubInput(final String[] value, Consumer<String> output) {
        this.value = value;
        this.output = output;
    }

    /**
     * Имитация последовательности действий пользровтеля.
     *
     * @param question вопрос системы.
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        output.accept(question);
        String answer = this.value[this.position++];
        output.accept(answer);
        return answer;
    }
    
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}