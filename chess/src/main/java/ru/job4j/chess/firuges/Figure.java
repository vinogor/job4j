package ru.job4j.chess.firuges;

public interface Figure {
    Cell position(); // позиция где фигура сейчас

    // проверка может ли так двигаться фигура
    // source - откуда
    // dest - куда
    // возвращает - последовательность клеток для перемещения
//    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;
    
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    // клон фигуры - для эффекта перемещения - старая удаляется, новая появляется
    
    Figure copy(Cell dest);

}
