package list;

public class SimpleStack<T> extends DynamicLinkedList<T> {

    // Метод poll() - должен возвращать значение и удалять его из коллекции.
    // Метод push(T value) - помещает значение в коллекцию.

    public T poll() {
        T result = get(0);
        delete();
        return result;
    }

    public void push(T value) {
        add(value);
    }
}
