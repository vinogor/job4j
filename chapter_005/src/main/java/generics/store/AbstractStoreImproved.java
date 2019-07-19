package generics.store;

import generics.SimpleArray;

public abstract class AbstractStoreImproved<T extends Base> implements Store<T> {

    private final SimpleArray<T> array;

    public AbstractStoreImproved(int len) {
        this.array = new SimpleArray<>(len);
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

//    @Override
//    public boolean replace(String id, T model) {
//        boolean result = false;
//        int index = 0;
//        for (T t : array) {
//            if (t.getId().equals(id)) {
//                array.set(index, model);
//                result = true;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }

    public boolean replace(String id, T model) {
        return someMethod(id, (array, index, t) -> {
            array.set(index, model);
            return true;
        });
    }

//    @Override
//    public boolean delete(String id) {
//        boolean result = false;
//        int index = 0;
//        for (T t : array) {
//            if (t.getId().equals(id)) {
//                array.remove(index);
//                result = true;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }

    public boolean delete(String id) {
        return someMethod(id, (array, index, t) -> {
            array.remove(index);
            return true;
        });
    }

//    @Override
//    public T findById(String id) {
//        T result = null;
//        int index = 0;
//        for (T t : array) {
//            if (t.getId().equals(id)) {
//                result = t;
//                break;
//            }
//            index++;
//        }
//        return result;
//    }

    public T findById(String id) {
        return someMethod(id, (array, index, t) -> t);
    }

    private <K> K someMethod(String id, TripleFunction<SimpleArray<T>, Integer, T, K> func) {
        K result = null;
        int index = 0;
        for (T t : array) {
            if (t.getId().equals(id)) {
                result = func.apply(array, index, t);
                break;
            }
            index++;
        }
        return result;
    }
}

