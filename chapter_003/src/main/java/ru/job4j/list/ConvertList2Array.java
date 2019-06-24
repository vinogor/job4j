package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int size = list.size();
        int cells = (size - 1) / rows + 1;

        //                    строки / столбцы
        int[][] array = new int[rows][cells];

        int row = 0;
        int cell = 0;

        for (Integer item : list ) {
            array[row][cell] = item;
            cell++;
            if (cell == cells) {
                cell = 0;
                row++;
            }
        }

        int emptyItems = row * cell - size;
        if (emptyItems != 0) {
            for (int i = 0; i < emptyItems; i++) {
                array[row][cell + i + 1] = 0;
            }
        }

        return array;
    }
}