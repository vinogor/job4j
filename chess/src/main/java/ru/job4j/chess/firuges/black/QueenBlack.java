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
public class QueenBlack implements Figure {
    private final Cell position;

    public QueenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
//        Cell[] steps = new Cell[0];
//        System.out.println("QueenBlack. Try to move from " + source.x + ":" + source.y + " to " + dest.x + ":" + dest.y);

        int[] deltasAndLength = calculateWayParam(source, dest);

        if (Arrays.equals(deltasAndLength, new int[3])) {
            throw new ImpossibleMoveException(this.getClass().getSimpleName() + " - ImpossibleMoveException");
        }

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

    private int[] calculateWayParam(Cell source, Cell dest) {
        int[] result = new int[3]; // deltaX, deltaY, length

        int diffX = source.x - dest.x;
        int diffY = source.y - dest.y;

        // сдвинулась с места?
        if (!((diffX == 0) && (diffY == 0))) {

            // по диагонали?
            if (Math.abs(diffX) == Math.abs(diffY)) {
//                System.out.println("    diagonal");
                result[0] = diffX > 0 ? -1 : 1;
                result[1] = diffY > 0 ? -1 : 1;
                result[2] = Math.abs(diffX);

                // по горизонтале?
            } else if (diffY == 0) {
//                System.out.println("    horizontal");
                result[0] = diffX > 0 ? -1 : 1;
                result[1] = 0;
                result[2] = Math.abs(diffX);

                // по вертикале?
            } else if (diffX == 0) {
//                System.out.println("    vertical");
                result[0] = 0;
                result[1] = diffY > 0 ? -1 : 1;
                result[2] = Math.abs(diffY);
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}
