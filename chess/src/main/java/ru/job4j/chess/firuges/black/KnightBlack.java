package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImpossibleMoveException;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightBlack implements Figure {
    private final Cell position;
    
    public KnightBlack(final Cell position) {
        this.position = position;
    }
    
    @Override
    public Cell position() {
        return this.position;
    }
    
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int absDiffX = Math.abs(source.x - dest.x);
        int absDiffY = Math.abs(source.y - dest.y);
        if (!( ((absDiffX == 1) && (absDiffY == 2)) || ((absDiffY == 1) && (absDiffX == 2)) )) {
            throw new ImpossibleMoveException(this.getClass().getSimpleName() + " - ImpossibleMoveException");
        }
        return new Cell[]{dest};
    }
    
    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}