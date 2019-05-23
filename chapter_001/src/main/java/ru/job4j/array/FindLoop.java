package ru.job4j.array;

/**
 * Find the index of the given value in the array.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class FindLoop {

    /**
     * Main calculations.
     *
     * @param data array
     * @param el   value of the item
     * @return index of item
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}