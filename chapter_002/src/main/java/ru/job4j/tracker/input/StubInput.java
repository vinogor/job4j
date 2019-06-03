package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

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
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}