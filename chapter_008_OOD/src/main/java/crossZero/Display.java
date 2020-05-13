package crossZero;

import crossZero.players.Player;

import java.io.*;

// всё что касается ввода/вывода на экран
public class Display {

    private InputStream inputStream = System.in;
    private Reader inputStreamReader = new InputStreamReader(inputStream);
    private BufferedReader br = new BufferedReader(inputStreamReader);
    private Board board;

    public Display(Board board) {
        this.board = board;
    }

    public void printBoard() {
        printLn("=== текущее состояние игровой доски");
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                print(board.getSign(i, j) + "  ");
            }
            printLn();
        }
    }

    public void greeting(int numOfVictoriesForWin) {
        printLn("=== Добро пожаловать на игру \"Крестики-нолики\"!");
        printLn("=== игра идёт до " + numOfVictoriesForWin + " побед");
    }

    public int askPlayer(String s) {
        int coordinate = 0;
        print(s);
        try {
            coordinate = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordinate;
    }

    public void endOfRound(Player player1, Player player2, int numOfVictoriesForWin) {
        printLn("=== текущий счёт: ");
        printLn("===     " + player1.getName() + " c символом " + player1.getSign() + " : " + player1.getCurrentVictories());
        printLn("===     " + player2.getName() + " c символом " + player2.getSign() + " : " + player2.getCurrentVictories());
        printLn("=== игра идёт до " + numOfVictoriesForWin + " побед");
    }

    public void printLn(String s) {
        System.out.println(s);
    }

    public void printLn() {
        printLn("");
    }

    public void print(String s) {
        System.out.print(s);
    }
}
