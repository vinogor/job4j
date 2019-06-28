package com;

import java.util.*;

public class Convert {

    // можно было не писать, он и так неявно создаётся
    public Convert(){

    }

    // модификатор public
    // Converts array to list
    List<Integer> makeList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                list.add(array[i][j]);
        }
        return list;
    }


    //Converts list to array
    public int[][] makeArray(List<Integer> list, int rws) {
        Iterator<Integer> iterator = list.iterator();
        int cls = list.size() / rws + (list.size() % rws == 0 ? 0 :
        1);
        int[][] array = new int[rws][cls];
        for (int i = 0; i < rws; i++) {
            for (int j = 0; j < cls; j++) {
                if (iterator.hasNext())
                    array[i][j] = iterator.next();
                else
                    // не обязательно присваивать ноль, они и так ноль при создании
                    array[i][j] = 0;
            }
        }
        return array;
    }
}