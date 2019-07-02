package ru.job4j.tracker;

import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;

import java.util.function.Consumer;

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
    
    private boolean doContinueWork = true;
    private int[] range;

    private final Consumer<String> output;
    
    /**
     * Field initialization constructor.
     *
     * @param input   data input.
     * @param tracker ticket storage.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Start program.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        Consumer<String> output = System.out::println;
        new StartUI(new ValidateInput(new ConsoleInput(output), output), new Tracker(), output).init();
//        new StartUI(new ValidateInput(new StubInput(new String[]{"invalid", "7"})), new Tracker()).init();
//        new StartUI(new ConsoleInput(), new Tracker()).init();
//        new StartUI(new StubInput(new String[]{"1", "name1", "disr1", "7"}), new Tracker()).init();
    }
    
    /**
     * The main cycle of the program.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions(this);
        
        fillRange(menu);
        
        int key;
        do {
            key = input.ask("Введите пункт меню (0.." + (menu.getActionsLength() - 1) + "): ", range);
            menu.select(key);
        } while (this.doContinueWork);
    }
    
    public void stop() {
        this.doContinueWork = false;
    }
    
    private void fillRange(MenuTracker menu) {
        int actionsLength = menu.getActionsLength();
        range = new int[actionsLength];
        for (int i = 0; i < actionsLength; i++) {
            range[i] = i;
        }
    }
    
//    ОТКЛЮЧИЛ ПОКА ЧТОБЫ НЕ ПЕРЕДЕЛЫВАТЬ ТЕСТЫ - пример анонимного класса
//
//        UserAction deleteAction = new UserAction() {
//            @Override
//            public int key() {
//                return 8;
//            }
//
//            @Override
//            public void execute(Input input, Tracker tracker) {
//                // todo
//            }
//
//            @Override
//            public String info() {
//                return "Delete All";
//            }
//        };
//        menu.addAction(deleteAction);
}