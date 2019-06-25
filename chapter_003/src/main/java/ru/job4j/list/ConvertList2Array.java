package ru.job4j.list;

import java.util.ArrayList;
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

    public List<Integer> convert (List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int value : array) {
                result.add(value);
            }
        }
        return result;
    }
}