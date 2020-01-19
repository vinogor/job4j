package crossZero;

import crossZero.players.Player;

public class Board {

    private String[][] board;
    private int size;
    private int freeCells;

    public Board() {
        this(3);
    }

    // нулевые поля используются для разметки и не играют
    public Board(int size) {
        this.freeCells = size * size;
        this.size = ++size;
        this.board = new String[size][size];
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 && j != 0) {
                    board[i][j] = Integer.toString(j);
                } else if (j == 0 && i != 0) {
                    board[i][j] = Integer.toString(i);
                } else {
                    board[i][j] = " ";
                }
            }
        }
    }

    public boolean add(String s, int r, int c) {
        if (!isCellFree(r, c)) {
            return false;
        }
        board[r][c] = s;
        freeCells--;
        System.out.println("свободных ячеек : " + freeCells);
        return true;
    }

    private boolean isCellFree(int r, int c) {
        // если такая ячейка существует и содержит пробел, то
        return r <= size && r > 0 && c <= size && c > 0 && board[r][c].equals(" ");
    }

    public boolean isPlayerWin(Player player) {

        String sign = player.getSign();

        // проверка диагоналей
        boolean leftDiag = true;
        boolean rightDiag = true;
        for (int i = 1; i < size; i++) {
            if (leftDiag && !board[i][i].equals(sign)) {
                leftDiag = false;
            }
            if (rightDiag && !board[i][size - i].equals(sign)) {
                rightDiag = false;
            }
        }

        if (leftDiag || rightDiag) {
            return true;
        }

        // проверка горизонталей и вертикалей
        boolean horiz = true;
        boolean vert = true;
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (horiz && !board[i][j].equals(sign)) {
                    horiz = false;
                }
                if (vert && !board[j][i].equals(sign)) {
                    vert = false;
                }
            }
            if (horiz || vert) {
                return true;
            }
            horiz = true;
            vert = true;
        }
        return false;
    }

    public String getSign(int r, int c) {
        return board[r][c];
    }

    public int getSize() {
        return size;
    }

    public void clean() {
        init();
        freeCells = (size - 1) * (size - 1);
    }

    public int getFreeCells() {
        return freeCells;
    }

    public int[] getCoordinateOfFreeCell() {
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (board[i][j].equals(" ")) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
