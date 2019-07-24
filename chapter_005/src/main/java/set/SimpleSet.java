package set;

import generics.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray;

    public SimpleSet() {
        this.simpleArray = new SimpleArray<>();
    }

    void add(E e) {
        checkAlreadyExist(e);
        simpleArray.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }

    private void checkAlreadyExist(E newE) {
        for (E e : simpleArray) {
            if ((e == null) && (newE == null)) {
                throw new IllegalArgumentException();
            }
            if ((newE != null) && (newE.equals(e))) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int size() {
        return simpleArray.size();
    }
}