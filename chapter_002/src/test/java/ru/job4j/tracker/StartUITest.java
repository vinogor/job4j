package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

//    private final PrintStream stdout = System.out;

    private final String nextLine = System.lineSeparator();

    private ITracker tracker;
    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void setUp() {
  //      System.setOut(new PrintStream(this.out));
        tracker = new Tracker();
        item1 = tracker.add(new Item("test name1", "test desc1", System.currentTimeMillis()));
        item2 = tracker.add(new Item("test name2", "test desc2", System.currentTimeMillis()));
        item3 = tracker.add(new Item("test name3", "test desc3", System.currentTimeMillis()));
    }

    @After
    public void tearDown() {
//        System.setOut(this.stdout);
    }

    @Test
    public void showMenuTest1() {
        String[] args = {"0", "7"};
        Input input = new StubInput(args, output);
        new StartUI(input, tracker, output).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[0]).append(nextLine)
                                .append("0 - Show menu").append(nextLine)
                                .append("1 - Add new Item").append(nextLine)
                                .append("2 - Show all items").append(nextLine)
                                .append("3 - Edit item").append(nextLine)
                                .append("4 - Delete item").append(nextLine)
                                .append("5 - Find item by Id").append(nextLine)
                                .append("6 - Find items by name").append(nextLine)
                                .append("7 - Exit Program").append(nextLine)
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[1]).append(nextLine)
                                .append("Завершаем работу программы.").append(nextLine)
                                .toString()
                )
        );
    }

    @Test
    public void addNewItemTest1() {
        Input input = new StubInput(new String[]{"1", "test name4", "test desc4", "7"}, output);

        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(3).getName(), is("test name4"));
    }

    @Test
    public void showAllTest1() {
        String[] args = {"2", "7"};
        Input input = new StubInput(args, output);
        new StartUI(input, tracker, output).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[0]).append(nextLine)
                                .append("------------ Вывод содержимого хранилища ----------").append(nextLine)
                                .append("Item{id='").append(item1.getId()).append("', name='test name1', decs='test desc1', time=").append(item1.getTime()).append("}").append(nextLine)
                                .append("Item{id='").append(item2.getId()).append("', name='test name2', decs='test desc2', time=").append(item2.getTime()).append("}").append(nextLine)
                                .append("Item{id='").append(item3.getId()).append("', name='test name3', decs='test desc3', time=").append(item3.getTime()).append("}").append(nextLine)
                                .append("------------ Конец --------------------------------").append(nextLine)
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[1]).append(nextLine)
                                .append("Завершаем работу программы.").append(nextLine)
                                .toString()
                )
        );
    }

    @Test
    public void editItemTest1() {
        Input input = new StubInput(new String[]{"3", item1.getId(), "test replace", "заменили заявку", "7"}, output);
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findItemById(item1.getId()).getName(), is("test replace"));
    }

    @Test
    public void delItemTest1() {
        Input input = new StubInput(new String[]{"4", item1.getId(), "7"}, output);
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().size(), is(2));
    }

    @Test
    public void findItemByIdTest1() {
        String[] args = {"5", item2.getId(), "7"};
        Input input = new StubInput(args, output);
        new StartUI(input, tracker, output).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[0]).append(nextLine)
                                .append("------------ Поиск заявки по id -------------------").append(nextLine)
                                .append("Введите id заявки: ").append(nextLine)
                                .append(args[1]).append(nextLine)
                                .append(" Заявка найдена: Item{id='").append(item2.getId()).append("', name='test name2', decs='test desc2', time=").append(item1.getTime()).append("}").append(nextLine)
                                .append("------------ Конец --------------------------------").append(nextLine)
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[2]).append(nextLine)
                                .append("Завершаем работу программы.").append(nextLine)
                                .toString()
                )
        );
    }

    @Test
    public void findItemByNameTest1() {
        item2.setName(item1.getName());
        String[] args = {"6", item1.getName(), "7"};
        Input input = new StubInput(args, output);
        new StartUI(input, tracker, output).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[0]).append(nextLine)
                                .append("------------ Поиск заявок по имени ----------------").append(nextLine)
                                .append("Введите имя заявки: ").append(nextLine)
                                .append(args[1]).append(nextLine)
                                .append(" Заявки найдены: ").append(nextLine)
                                .append("Item{id='").append(item1.getId()).append("', name='test name1', decs='test desc1', time=").append(item1.getTime()).append("}").append(nextLine)
                                .append("Item{id='").append(item2.getId()).append("', name='test name1', decs='test desc2', time=").append(item1.getTime()).append("}").append(nextLine)
                                .append("------------ Конец --------------------------------").append(nextLine)
                                .append("Введите пункт меню (0..7): ").append(nextLine)
                                .append(args[2]).append(nextLine)
                                .append("Завершаем работу программы.").append(nextLine)
                                .toString()
                )
        );
    }
}