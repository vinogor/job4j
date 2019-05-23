package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Andreev Aleksandr (andreev.aleksandr.spb@gmail.com)
 * @version 1.2
 * @since 22.05.2019
 */
public class Paint {

    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

//    public String rightTrl(int height) {
//        // Буфер для результата.
//        StringBuilder screen = new StringBuilder();
//        // ширина будет равна высоте.
//        int width = height;
//        // внешний цикл двигается по строкам.
//        for (int row = 0; row != height; row++) {
//            // внутренний цикл определяет положение ячейки в строке.
//            for (int column = 0; column != width; column++) {
//                // если строка равна ячейке, то рисуем галку.
//                // в данном случае строка определяет, сколько галок будет в строке
//                if (row >= column) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            // добавляем перевод строки.
//            screen.append(System.lineSeparator());
//        }
//        // Получаем результат.
//        return screen.toString();
//    }

    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

//    public String leftTrl(int height) {
//        StringBuilder screen = new StringBuilder();
//        int width = height;
//        for (int row = 0; row != height; row++) {
//            for (int column = 0; column != width; column++) {
//                if (row >= width - column - 1) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            screen.append(System.lineSeparator());
//        }
//        return screen.toString();
//    }


    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

//    public String pyramid(int height) {
//        StringBuilder screen = new StringBuilder();
//        int width = 2 * height - 1;
//        for (int row = 0; row != height; row++) {
//            for (int column = 0; column != width; column++) {
//                if (row >= height - column - 1 && row + height - 1 >= column) {
//                    screen.append("^");
//                } else {
//                    screen.append(" ");
//                }
//            }
//            screen.append(System.lineSeparator());
//        }
//        return screen.toString();
//    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}