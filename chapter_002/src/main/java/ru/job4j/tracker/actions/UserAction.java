package ru.job4j.tracker.actions;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;

public interface UserAction {
    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();
    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    void execute(Input input, ITracker tracker);
    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}