package ru.job4j.tracker;

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
        new StartUI(new ConsoleInput(), new Tracker()).init();
//        new StartUI(new StubInput(new String[]{"1", "name1", "disr1", "7"}), new Tracker()).init();
    }

    /**
     * The main cycle of the program.
     */
    public void init() {

        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();

        int key;
        do {
            key = Integer.valueOf(input.ask("Введите пункт меню (0.." + (menu.getActionsLength() - 1) + "): "));
            while ((key < 0) || (key > menu.getActionsLength() - 1)) {
                System.out.println("Введён несуществующий пункт меню, введите значение заново!");
                key = Integer.valueOf(input.ask("Введите пункт меню: "));
            }
            if (key == 0) {
                menu.show();
            } else if (key == 7) {
                System.out.println("Завершаем работу программы.");
                break;
            } else {
                menu.select(key);
            }
        } while (true);
//        } while (!"y".equals(this.input.ask("Exit? (y): ")));

//        boolean exit = false;
//        while (!exit) {
//            String answer = this.input.ask("Введите пункт меню (0..7): ");
//            switch (answer) {
//                case MENU:
//                    this.showMenu();
//                    break;
//                case ADD:
//                    this.createItem();
//                    break;
//                case SHOW_ALL:
//                    this.showAll();
//                    break;
//                case EDIT:
//                    this.edit();
//                    break;
//                case DEL:
//                    this.delete();
//                    break;
//                case FIND_BY_ID:
//                    this.findById();
//                    break;
//                case FIND_BY_NAME:
//                    this.findByName();
//                    break;
//                case EXIT:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Ничего непонятно, напиши ещё разок!");
//            }
//        }
    }

    /**
     * Show menu.
     */
    private void showMenu() {
        String nextLine = System.lineSeparator();
        StringBuilder sb = new StringBuilder()
                .append(" - Menu - ").append(nextLine)
                .append("0. Show menu").append(nextLine)
                .append("1. Add new Item").append(nextLine)
                .append("2. Show all items").append(nextLine)
                .append("3. Edit item").append(nextLine)
                .append("4. Delete item").append(nextLine)
                .append("5. Find item by Id").append(nextLine)
                .append("6. Find items by name").append(nextLine)
                .append("7. Exit Program").append(nextLine);
        System.out.println(sb);
    }

    /**
     * Create and add new ticket to storage.
     */
    private void createItem() {
//        System.out.println("------------ Добавление новой заявки --------------");
//        String name = this.input.ask("Введите имя заявки: ");
//        String desc = this.input.ask("Введите описание заявки: ");
//        Item item = new Item(name, desc, System.currentTimeMillis());
//        this.tracker.add(item);
//        System.out.println("------------ Новая заявка с id = " + item.getId() + " добавлена -----------");
    }

    /**
     * Show all tickets from storage.
     */
    private void showAll() {
//        System.out.println("------------ Вывод содержимого хранилища ----------");
//        Item[] items = this.tracker.findAll();
//        for (Item item : items) {
//            System.out.println(item);
//        }
//        System.out.println("------------ Конец --------------------------------");
    }

    /**
     * Edit ticket with given id.
     */
    private void edit() {
//        System.out.println("------------ Изменение имеющейся заявки -----------");
//        String id = this.input.ask("Введите id заявки: ");
//        String name = this.input.ask("Введите новое имя заявки: ");
//        String desc = this.input.ask("Введите новое описание заявки: ");
//        Item item = new Item(name, desc, System.currentTimeMillis());
//        boolean result = this.tracker.replace(id, item);
//        if (result) {
//            System.out.println(" Заявка обновлена ");
//        } else {
//            System.out.println(" Заявки с таким id не найдено ");
//        }
//        System.out.println("------------ Конец --------------------------------");
    }

    /**
     * Delete ticket with given id.
     */
    private void delete() {
//        System.out.println("------------ Удаление заявки ----------------------");
//        String id = this.input.ask("Введите id заявки: ");
//        boolean result = this.tracker.delete(id);
//        if (result) {
//            System.out.println(" Заявка удалена ");
//        } else {
//            System.out.println(" Заявки с таким id не найдено ");
//        }
//        System.out.println("------------ Конец --------------------------------");
    }

    /**
     * Show ticket with given id.
     */
    private void findById() {
//        System.out.println("------------ Поиск заявки по id -------------------");
//        String id = this.input.ask("Введите id заявки: ");
//        Item result = this.tracker.findItemById(id);
//        if (result != null) {
//            System.out.println(" Заявка найдена: " + result);
//        } else {
//            System.out.println(" Заявок с таким id не найдено");
//        }
//        System.out.println("------------ Конец --------------------------------");
    }

    /**
     * Show tickets with given name.
     */
    private void findByName() {
//        System.out.println("------------ Поиск заявок по имени ----------------");
//        String name = this.input.ask("Введите имя заявки: ");
//        Item[] items = this.tracker.findItemsByName(name);
//        if (items.length > 0) {
//            System.out.println(" Заявки найдены: ");
//            for (Item item : items) {
//                System.out.println(item);
//            }
//        } else {
//            System.out.println(" Заявки с таким именем не найдены ");
//        }
//        System.out.println("------------ Конец --------------------------------");
    }
}