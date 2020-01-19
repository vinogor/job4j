package crossZero.players;

import crossZero.Display;

public class HPlayer implements Player {

    private String name;
    private String sign;
    private Display display;
    private int currentVictories = 0;

    public HPlayer(String name, String sign, Display display) {
        this.name = name;
        this.sign = sign;
        this.display = display;
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
        return display.askPlayer(s);
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
