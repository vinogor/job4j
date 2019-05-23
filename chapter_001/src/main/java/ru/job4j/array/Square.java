package ru.job4j.array;

/**
 * Fill the array with squares of numbers in the range from 1 to the specified number.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class Square {

    /**
     * Main calculations.
     *
     * @param bound max number of range
     * @return result
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (int) Math.pow((i+1), 2);
        }
        return rst;
    }
}