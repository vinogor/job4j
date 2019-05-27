package ru.job4j.condition;

public class Max {

    public int max(int arg1, int arg2) {
        return arg2 > arg1 ? arg2 : arg1;
    }

    public int max(int arg1, int arg2, int arg3) {
        return max(max(arg1, arg2), arg3);
    }

    public int max(int arg1, int arg2, int arg3, int arg4) {
        return max(max(max(arg1, arg2), arg3), arg4);
    }
}