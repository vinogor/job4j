package job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return checkWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return checkWinner(Figure3T::hasMarkO);
    }

    //  проверяет, если ли пустые клетки для новых ходов

    public boolean hasGap() {
        return Arrays.stream(table)             // создали стрим из массива с массивами
            .flatMap(Stream::of)                // сделали его одномерным
            .anyMatch(new Figure3T()::equals);  // если нет пустых то TRUE
    }

    private boolean checkWinner(Predicate<Figure3T> predicate) {
        // горизонталь
        return this.fillBy(predicate, 0, 0, 1, 0) ||
            this.fillBy(predicate, 0, 1, 1, 0) ||
            this.fillBy(predicate, 0, 2, 1, 0) ||
            // вертикаль
            this.fillBy(predicate, 0, 0, 0, 1) ||
            this.fillBy(predicate, 1, 0, 0, 1) ||
            this.fillBy(predicate, 2, 0, 0, 1) ||
            // диагонали
            this.fillBy(predicate, 0, 0, 1, 1) ||
            this.fillBy(predicate, this.table.length - 1, 0, -1, 1);
    }
}