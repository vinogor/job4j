package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImpossibleMoveException;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;
    
    public PawnBlack(final Cell position) {
        this.position = position;
    }
    
    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    // пешка
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        // попытка перемещения корректна?
        // только вверх, не более чем на 2 клетки, не остался на месте, не более чем на 1 кл если не со старта
        if ((source.x != dest.x) || (source.y - dest.y > 2) || (source.y - dest.y <= 0) || ((source.y - dest.y > 1) && (source.y != 6))) {
            throw new ImpossibleMoveException(this.getClass().getSimpleName() + " - ImpossibleMoveException");
        }

        // создаём нулёвую клетку
        Cell[] steps = new Cell[0];

        // на 1 клетку вверх?
        if (source.y == dest.y + 1) {
            steps = new Cell[]{dest};

        // на 2 клетки вверх? (если с начальной позиции)
        } else if ((source.y == dest.y + 2) && (source.y == 6)) {
            steps = new Cell[]{Cell.values()[source.x * 8 + dest.y + 1], dest};
            // Cell.values()[currentX * 8 + currentY]
        }
        return steps;
    }
    
    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
