package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImpossibleMoveException;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KingBlack implements Figure {
    private final Cell position;
    
    public KingBlack(final Cell position) {
        this.position = position;
    }
    
    @Override
    public Cell position() {
        return this.position;
    }
    
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
//        Cell[] steps = new Cell[0];
//        System.out.println("KingBlack. Try to move from " + source.x + ":" + source.y + " to " + dest.x + ":" + dest.y);
        // на 1 клетку куда угодно
        if ((Math.abs(source.x - dest.x)) > 1 || (Math.abs(source.y - dest.y) > 1) ||
                ((Math.abs(source.x - dest.x)) == 0 && (Math.abs(source.y - dest.y) == 0))) {
//            steps = new Cell[]{dest};
            throw new ImpossibleMoveException(this.getClass().getSimpleName() + " - ImpossibleMoveException");
        }
        return new Cell[]{dest};
    }
    
    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
