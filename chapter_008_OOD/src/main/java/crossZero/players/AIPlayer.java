package crossZero.players;

import crossZero.Board;
import crossZero.Display;

public class AIPlayer implements Player {

    private String name;
    private String sign;
    private Display display;
    private Board board;
    private int currentVictories = 0;
    private boolean isHorizontal = true;

    public AIPlayer(String name, String sign, Display display, Board board) {
        this.name = name;
        this.sign = sign;
        this.display = display;
        this.board = board;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSign() {
        return sign;
    }

    @Override
    public int getCoordinate(String s) {
        int[] coordinateOfFreeCell = board.getCoordinateOfFreeCell();
        int coordinate;
        if (isHorizontal) {
            isHorizontal = false;
            coordinate = coordinateOfFreeCell[0];
            display.printLn("компьютер ввёл номер строки: " + coordinate);
        } else {
            isHorizontal = true;
            coordinate = coordinateOfFreeCell[1];
            display.printLn("компьютер ввёл номер столбца: " + coordinate);
        }
        return coordinate;
    }

    @Override
    public int getCurrentVictories() {
        return currentVictories;
    }

    @Override
    public void incCurrentVictories() {
        currentVictories++;
    }
}
