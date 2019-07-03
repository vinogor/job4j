package job4j.tictactoe;

import javafx.scene.shape.Rectangle;

public class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;

    public Figure3T() {
    }

    public Figure3T(boolean markX, boolean markO) {
        this.markX = markX;
        this.markO = markO;
    }

    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public boolean hasMarkX() {
        return this.markX;
    }

    public boolean hasMarkO() {
        return this.markO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure3T figure3T = (Figure3T) o;

        if (markX != figure3T.markX) return false;
        return markO == figure3T.markO;
    }

    @Override
    public int hashCode() {
        int result = (markX ? 1 : 0);
        result = 31 * result + (markO ? 1 : 0);
        return result;
    }
}
