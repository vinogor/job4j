package ru.job4j.tracker;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;

/**
 * Start dialog with user via console.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 29.05.2019
 */
public class StartUI {
    
    private final Input input;
    private final Tracker tracker;
    
    private int[] range;
    
    /**
     * Field initialization constructor.
     *
     * @param input   data input.
     * @param tracker ticket storage.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    
    /**
     * Start program.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
//        new StartUI(new ConsoleInput(), new Tracker()).init();
//        new StartUI(new StubInput(new String[]{"1", "name1", "disr1", "7"}), new Tracker()).init();
    }
    
    /**
     * The main cycle of the program.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        fillRange(menu);
        
        int key;
        do {
            key = input.ask("Введите пункт меню (0.." + (menu.getActionsLength() - 1) + "): ", range);
            if (key == 0) {
                menu.show();
            } else if (key == 7) {
                System.out.println("Завершаем работу программы.");
                break;
            } else {
                menu.select(key);
            }
        } while (true);
    }
    
    private void fillRange(MenuTracker menu) {
        int actionsLength = menu.getActionsLength();
        range = new int[actionsLength];
        for (int i = 0; i < actionsLength; i++) {
            range[i] = i;
        }
    }
}