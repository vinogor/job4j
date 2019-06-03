package ru.job4j.tracker.input;

import ru.job4j.tracker.input.Input;

public class StubInput implements Input {
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
    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Имитация последовательности действий пользровтеля.
     *
     * @param question вопрос системы.
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        String answer = this.value[this.position++];
        System.out.println(answer);
        return answer;
    }
    
    @Override
    public int ask(String question, int[] range) {
//        throw new UnsupportedOperationException("Unsupported operation");
        return -1;
    }
}