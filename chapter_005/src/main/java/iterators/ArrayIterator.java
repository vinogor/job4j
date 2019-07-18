package iterators;

import java.util.Iterator;

public class ArrayIterator implements Iterator {
    //    строки | столбцы
    private int[][] value;
    // начальные позиции указателя:
    private int indexI = 0;
    private int indexJ = 0;

    public ArrayIterator(int[][] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        // если указатель перешёл на несуществующую строку
            return !(indexI == value.length);
    }

    @Override
    public Object next() {
        // возвращает ТЕКУЩИЙ и сдвигает указатель
        Object result = value[indexI] [indexJ++];
        if (indexJ == value[indexI].length) {
            indexJ = 0;
            indexI++;
        }
        return result;
    }
}