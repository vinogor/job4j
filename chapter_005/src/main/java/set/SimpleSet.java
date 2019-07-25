package set;

import generics.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> simpleArray;

    public SimpleSet() {
        this.simpleArray = new SimpleArray<>();
    }

    void add(E e) {
        if (!checkAlreadyExist(e)) {
            simpleArray.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return simpleArray.iterator();
    }

    private boolean checkAlreadyExist(E newE) {
        boolean result = false;
        for (E e : simpleArray) {
            if (((e == null) && (newE == null)) || ((newE != null) && (newE.equals(e)))) {
                result = true;
                break;
            }
        }
        return result;
    }

    public int size() {
        return simpleArray.size();
    }
}