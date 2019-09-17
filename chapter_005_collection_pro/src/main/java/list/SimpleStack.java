package list;

public class SimpleStack<T> {

    private DynamicLinkedList<T> dynamicLinkedList = new DynamicLinkedList<>();

    // Метод poll() - должен возвращать значение и удалять его из коллекции.
    // Метод push(T value) - помещает значение в коллекцию.

    public T poll() {
        return dynamicLinkedList.delete();
    }

    public void push(T value) {
        dynamicLinkedList.add(value);
    }

    public int size() {
        return dynamicLinkedList.getSize();
    }

    public T get(int index) {
        return dynamicLinkedList.get(index);
    }
}