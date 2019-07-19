package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<E> implements Iterable<E> {

    private int len = 10;
    private int cursor = 0;
    private E[] array;

    public DynamicArray(int len) {
        this.len = len;
        this.array = (E[]) new Object[len];
    }

    public DynamicArray() {
        this.array = (E[]) new Object[len];
    }

    public void add(E model) {
        if (cursor == len) {
            expandArray();
        }
        this.array[this.cursor++] = model;
    }

    public void set(int index, E model) {
        this.array[index] = model;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, len - index - 1);
        this.cursor--;
    }

    public E get(int index) {
        return this.array[index];
    }

    public int size() {
        return len;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursorIter = 0;

            @Override
            public boolean hasNext() {
                return cursorIter != cursor;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[cursorIter++];
            }
        };
    }

    private void expandArray() {
        int newLen = len * 2;
        E[] newArray = (E[]) new Object[newLen];
        System.arraycopy(array, 0, newArray, 0, len);
        len = newLen;
        array = newArray;
    }
}