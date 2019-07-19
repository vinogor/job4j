package list;

public class SimpleQueue<T> {

//    Метод push(T value) - помещает значение в коллекцию.
//    Метод poll() - должен возвращать значение С КОНЦА и удалять его из коллекции.

    private SimpleStack<T> tempSimpleStack1 = new SimpleStack<>();
    private SimpleStack<T> tempSimpleStack2 = new SimpleStack<>();

    public void push(T value) {
        tempSimpleStack1.push(value);
    }

    public T poll() {
        int tempSize = tempSimpleStack1.size();
        for (int i = 0; i < tempSize - 1; i++) {
            tempSimpleStack2.push(tempSimpleStack1.poll());
        }

        T result = tempSimpleStack1.poll();

        tempSize = tempSimpleStack2.size();
        for (int i = 0; i < tempSize; i++) {
            tempSimpleStack1.push(tempSimpleStack2.poll());
        }
        return result;
    }
}