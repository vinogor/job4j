package crossZero;

import crossZero.players.Player;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Display display;
    private int numOfVictoriesForWin;

    public Game(Board board, Player player1, Player player2, Display display, int numOfVictoriesForWin) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.display = display;
        this.numOfVictoriesForWin = numOfVictoriesForWin;
    }

    public void start() {
        display.greeting(numOfVictoriesForWin);
        while (numOfVictoriesForWin != player1.getCurrentVictories() && numOfVictoriesForWin != player2.getCurrentVictories()) {
            display.printBoard();
            startRound();
            display.endOfRound(player1, player2, numOfVictoriesForWin);
            board.clean();
        }
        String winner = numOfVictoriesForWin == player1.getCurrentVictories() ? player1.getName() : player2.getName();
        display.printLn("=== игра окончена, победил " + winner);
    }

    private void startRound() {
        while (true) {
            if (checkFreeCell()) break;
            addNewSignOnBoard(player1);
            if (checkWinner(player1)) break;

            if (checkFreeCell()) break;
            addNewSignOnBoard(player2);
            if (checkWinner(player2)) break;
        }
    }

    private boolean checkFreeCell() {
        if (board.getFreeCells() == 0) {
            display.printLn("=== в текущем раунде ничья");
            return true;
        }
        return false;
    }

    private boolean checkWinner(Player player) {
        if (board.isPlayerWin(player)) {
            player.incCurrentVictories();
            display.printLn("=== в текущем раунде победил игрок " + player.getName());
            return true;
        }
        return false;
    }

    private void addNewSignOnBoard(Player player) {
        display.printLn("=== ходит " + player.getName() + ", символ \"" + player.getSign() + "\"");
        int r;
        int c;
        boolean result;
        do {
            r = player.getCoordinate("Введите номер строки: ");
            c = player.getCoordinate("Введите номер колонки: ");
            if (!(result = board.add(player.getSign(), r, c))) {
                display.printLn("=== неверно введены координаты, попробуйте ещё раз");
            }
        } while (!result);
        display.printBoard();
    }
}
