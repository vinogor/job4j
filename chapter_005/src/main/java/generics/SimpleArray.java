package generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private final int len;
    private int cursor = 0;
    private T[] array;

    public SimpleArray(int len) {
        this.len = len;
        this.array = (T[]) new Object[len];
    }

    public void add(T model) {
        if (cursor == len) {
            throw new ArrayIndexOutOfBoundsException("Array overflow");
        }
        this.array[this.cursor++] = model;
    }

    public void set(int index, T model) {
        this.array[index] = model;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, len - index - 1);
        this.cursor--;
    }

    public T get(int index) {
        return this.array[index];
    }

    //  метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int cursorIter = 0;

            @Override
            public boolean hasNext() {
                return cursorIter != cursor;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[cursorIter++];
            }
        };
    }
}