package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Создать итератор возвращающий только четные цифры.
// Итератор должен принимать список произвольных чисел.
public class EvenIterator implements Iterator {
    private int[] value;
    private int index = 0;

    public EvenIterator(int[] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        // либо нет чётных, либо закончился массив
        boolean result = false;
        for (int i = index; i < value.length; i++) {
            if (value[i] % 2 == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (value[index++]);
    }
}