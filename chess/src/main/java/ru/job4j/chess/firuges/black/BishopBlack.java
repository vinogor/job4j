package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImpossibleMoveException;

import java.util.Arrays;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    // офицер
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        // проверяем может ли фигура вообще так двигаться:
        if (!isOnDiagonal(source, dest) || source.equals(dest)) {
            throw new ImpossibleMoveException(this.getClass().getSimpleName() + " - ImpossibleMoveException");
        }

        int[] deltasAndLength = calculateWayParam(source, dest);

        int deltaX = deltasAndLength[0];
        int deltaY = deltasAndLength[1];
        int len = deltasAndLength[2];
        Cell[] steps = new Cell[len];

        int currentX = source.x;
        int currentY = source.y;

        for (int i = 0; i < len; i++) {
            currentX += deltaX;
            currentY += deltaY;
            steps[i] = Cell.values()[currentX * 8 + currentY];
        }
        return steps;
    }

    private boolean isOnDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }

    private int[] calculateWayParam(Cell source, Cell dest) {
        int[] result = new int[3]; // deltaX, deltaY, length

        int diffX = source.x - dest.x;
        int diffY = source.y - dest.y;

        result[0] = diffX > 0 ? -1 : 1;
        result[1] = diffY > 0 ? -1 : 1;
        result[2] = Math.abs(diffX);

        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}