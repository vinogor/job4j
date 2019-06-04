package ru.job4j.chess;

import ru.job4j.chess.firuges.*;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    // перемещение фигуры
    // доработать - поедание
//
//    Метод должен проверить
//   + Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
//   + Проверить что полученный путь не занят фигурами. Если занят выкинуть исключение
//   + Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        int index = this.findBy(source);

        // если фигура не существует в указанной клетке, то эксепшн
        if (index == -1) {
            throw new FigureNotFoundException("FigureNotFoundException");
        }

        // вычисляем путь для перемещения (последовательность клеток)
        Cell[] steps = this.figures[index].way(source, dest);

        // проверка не занят ли путь другими фигурами
        // (для Коня - исключение, путь равен 1 клетке = месту назначения, isWayFree() всегда true )
        isWayFree(steps);

        // проверить есть ли кто в конечной точке, если да, то проверить можно ли есть
        canEatFigure(steps[steps.length - 1]);

        // если путь
        //      не нулевой (а с чего вдруг нулевой??)
        //      и путь свободен
        //      и можно есть эту фигуру (другого цвета)
        //      и последняя клетка пути = клетка назначения (а с чего может быть по-другому??)

        //      if (steps.length > 0 && isWayFree(steps) && canEatFigure(steps[steps.length - 1]) && steps[steps.length - 1].equals(dest)) {
        //      rst = true;

        // перемещаем фигуру (копируем с новыми координатами)
        this.figures[index] = this.figures[index].copy(dest);
        return true;
    }

    // проверка конечной клетки
    private boolean canEatFigure(Cell lastStep) throws OccupiedWayException {
        // если в месте назначения уже есть фигура, то...
        if (findBy(lastStep) != -1) {
            // пока есть фигуры нельзя
            // потом прописать проверку на цвет и решить можно ли есть
            throw new OccupiedWayException("OccupiedWayException");
        }
        return true;
    }

    // проверка - свободен ли путь, без проверки последней клетки
    private boolean isWayFree(Cell[] steps) throws OccupiedWayException {
        for (int i = 0; i < steps.length - 1; i++) {
            if (findBy(steps[i]) != -1) {
                throw new OccupiedWayException("OccupiedWayException");
            }
        }
        return true;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        // индекс - это индивидуальный номер фигуры
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}