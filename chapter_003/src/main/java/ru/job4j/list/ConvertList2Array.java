package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        double size = list.size();
        int cells = (int) Math.ceil(size / rows);
        int[][] arrayResult = new int[rows][cells];

        int row = 0;
        int cell = 0;

        for (Integer item : list) {
            arrayResult[row][cell] = item;
            cell++;
            if (cell == cells) {
                cell = 0;
                row++;
            }
        }
        return arrayResult;
    }
}