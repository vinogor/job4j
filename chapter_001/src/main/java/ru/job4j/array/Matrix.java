package ru.job4j.array;

/**
 * Multiplication table.
 *
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1
 * @since 23.05.2019
 */
public class Matrix {

    /**
     * Main calculations.
     *
     * @param size size of matrix.
     * @return multiplication table array.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}