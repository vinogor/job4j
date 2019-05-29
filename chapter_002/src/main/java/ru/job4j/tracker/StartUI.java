package ru.job4j.tracker;

/**
 * Start dialog with user via console.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 29.05.2019
 */
public class StartUI {

	private static final String ADD = "1";
	private static final String SHOWALL = "2";
	private static final String EDIT = "3";
	private static final String DEL = "4";
	private static final String FINDBYID = "5";
	private static final String FINDBYNAME = "6";
	private static final String EXIT = "7";

	private final ConsoleInput input;
	private final Tracker tracker;

	/**
	 * Field initialization constructor.
	 *
	 * @param input   data input.
	 * @param tracker ticket storage.
	 */
	public StartUI(ConsoleInput input, Tracker tracker) {
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
	}

	/**
	 * The main cycle of the program.
	 */
	public void init() {
		boolean exit = false;
		while (!exit) {
			this.showMenu();
			String answer = this.input.ask("Введите пункт меню: ");
			switch (answer) {
				case ADD:
					this.createItem();
					break;
				case SHOWALL:
					this.showAll();
					break;
				case EDIT:
					this.edit();
					break;
				case DEL:
					this.delete();
					break;
				case FINDBYID:
					this.findById();
					break;
				case FINDBYNAME:
					this.findByName();
					break;
				case EXIT:
					exit = true;
					break;
				default:
					System.out.println("Ничего непонятно, напиши ещё разок!");
			}
		}
	}

	/**
	 * Show menu.
	 */
	private void showMenu() {
		StringBuilder sb = new StringBuilder();
		String nextLine = System.lineSeparator();
		sb.append(" - Menu - ").append(nextLine);
		sb.append("1. Add new Item").append(nextLine);
		sb.append("2. Show all items").append(nextLine);
		sb.append("3. Edit item").append(nextLine);
		sb.append("4. Delete item").append(nextLine);
		sb.append("5. Find item by Id").append(nextLine);
		sb.append("6. Find items by name").append(nextLine);
		sb.append("7. Exit Program").append(nextLine);
		System.out.println(sb);
	}

	/**
	 * Create and add new ticket to storage.
	 */
	private void createItem() {
		System.out.println("------------ Добавление новой заявки --------------");
		String name = this.input.ask("Введите имя заявки: ");
		String desc = this.input.ask("Введите описание заявки: ");
		Item item = new Item(name, desc, System.currentTimeMillis());
		this.tracker.add(item);
		System.out.println("------------ Новая заявка с id = " + item.getId() + " добавлена -----------");

	}

	/**
	 * Show all tickets from storage.
	 */
	private void showAll() {
		System.out.println("------------ Вывод содержимого хранилища ----------");
		Item[] items = this.tracker.findAll();
		for (Item item : items) {
			System.out.println(item);
		}
		System.out.println("------------ Конец --------------------------------");
	}

	/**
	 * Edit ticket with given id.
	 */
	private void edit() {
		System.out.println("------------ Изменение имеющейся заявки -----------");
		String id = this.input.ask("Введите id заявки: ");
		String name = this.input.ask("Введите новое имя заявки: ");
		String desc = this.input.ask("Введите новое описание заявки: ");
		Item item = new Item(name, desc, System.currentTimeMillis());
		boolean result = this.tracker.replace(id, item);
		if (result) {
			System.out.println(" Заявка обновлена ");
		} else {
			System.out.println(" Заявки с таким id не найдено ");
		}
		System.out.println("------------ Конец --------------------------------");
	}

	/**
	 * Delete ticket with given id.
	 */
	private void delete() {
		System.out.println("------------ Удаление заявки ----------------------");
		String id = this.input.ask("Введите id заявки: ");
		boolean result = this.tracker.delete(id);
		if (result) {
			System.out.println(" Заявка удалена ");
		} else {
			System.out.println(" Заявки с таким id не найдено ");
		}
		System.out.println("------------ Конец --------------------------------");
	}

	/**
	 * Show ticket with given id.
	 */
	private void findById() {
		System.out.println("------------ Поиск заявки по id -------------------");
		String id = this.input.ask("Введите id заявки: ");
		Item result = this.tracker.findItemById(id);
		if (result != null) {
			System.out.println(" Заявка найдена: " + result);
		} else {
			System.out.println(" Заявок с таким id не найдено");
		}
		System.out.println("------------ Конец --------------------------------");
	}

	/**
	 * Show tickets with given name.
	 */
	private void findByName() {
		System.out.println("------------ Поиск заявок по имени ----------------");
		String name = this.input.ask("Введите имя заявки: ");
		Item[] items = this.tracker.findItemsByName(name);
		if (items.length > 0) {
			System.out.println(" Заявки найдены: ");
			for (Item item : items) {
				System.out.println(item);
			}
		} else {
			System.out.println(" Заявки с таким именем не найдены ");
		}
		System.out.println("------------ Конец --------------------------------");
	}
}