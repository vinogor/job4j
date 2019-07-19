package generics.store;

import generics.SimpleArray;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> array;

    public AbstractStore(int len) {
        this.array = new SimpleArray<>(len);
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = 0;
        for (T t : array) {
            if (t.getId().equals(id)) {
                array.set(index, model);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        for (T t : array) {
            if (t.getId().equals(id)) {
                array.remove(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = 0;
        for (T t : array) {
            if (t.getId().equals(id)) {
                result = t;
                break;
            }
            index++;
        }
        return result;
    }
}