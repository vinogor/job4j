package map.custom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashMap<K, V> implements InterfaceForCustomMap<K, V>, Iterable {

    // + Внутренняя реализация должна использовать массив.
    // + Нужно обеспечить фиксированное время вставки и получение.
    // + Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
    //
    // Методы разрешения коллизий реализовывать не надо.
    // Например: если при добавлении ключ уже есть, то возвращать false.

    private int lenArray;
    private int quantity;
    private Node<K, V>[] array;

    public CustomHashMap() {
        this.lenArray = 16;
        this.array = new Node[lenArray];
    }

    public CustomHashMap(int i) {
        this.lenArray = i;
        this.array = new Node[lenArray];
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = generateIndex(key);
        if ((array[index] != null) && (array[index].key.equals(key))) {
            result = array[index].value;
        }
        return result;
    }

    @Override
    public boolean delete(K key) {
        boolean result = false;
        int index = generateIndex(key);
        if ((array[index] != null) && (array[index].key.equals(key))) {
            array[index] = null;
            quantity--;
            result = true;
        }
        return result;
    }

    // а если не null то нужно сравнить ключи и если они равны то заменить значение
    @Override
    public boolean insert(K key, V value) {
        boolean result = false;
        isNeedResize();
        int index = generateIndex(key);
        if (array[index] == null) {
            array[index] = new Node<>(key, value);
            quantity++;
            result = true;
        } else if (array[index].key.equals(key)) {
            array[index].value = value;
            result = true;
        }
        return result;
    }

    public int size() {
        return quantity;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int tempQuantityOfReturned = 0;

            @Override
            public boolean hasNext() {
                return tempQuantityOfReturned != quantity;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node result = null;
                for (int i = 0; i < lenArray; i++) {
                    if (array[i] != null) {
                        tempQuantityOfReturned++;
                        result = array[i];
                    }
                }
                return result;
            }
        };
    }

    private int generateIndex(K key) {
        //      1й вариант через остаток от деления модуля хэшкода числа
        // return Math.abs(key.hashCode()) % lenArray;
        return key.hashCode() % lenArray;

        //      2й вариант через побитовый беззнаковый сдвиг вправо
        //      беззнаковый - значит результат будет всегда положительное число
        //      32 - степень int, неизменно
        //      Math.log(lenArray)/Math.log(2) - логарифм размера массива по основанию 2, т.е. от 16 => 4
        // return key.hashCode() >>> (int) (32 - (Math.log(lenArray) / Math.log(2)));
    }

    private void isNeedResize() {
        if (quantity == lenArray) {
            this.lenArray *= 2;
            Node<K, V>[] tempArray = this.array;
            this.array = new Node[lenArray];
            quantity = 0;
            for (Node<K, V> node : tempArray) {
                insert(node.key, node.value);
            }
        }
    }
}