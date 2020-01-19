package crossZero;

import crossZero.players.AIPlayer;
import crossZero.players.HPlayer;
import crossZero.players.Player;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();

        // выбор размера поля через конструктор
        // Board board = new Board(4);
        Display display = new Display(board);

        Player player1 = new HPlayer("Игрок-человек 1", "X", display);

        // Player player2 = new HPlayer("Игрок-человек 2", "O", display);
        Player player2 = new AIPlayer("Игрок-КОМПьютер", "O", display, board);

        // устанавливаем кол-во побед
        int numOfVictoriesForWin = 3;

        // кто будет первым ходить - определяется очерёдность аргументов-игроков
        Game game = new Game(board, player1, player2, display, numOfVictoriesForWin);

        game.start();
    }
}
