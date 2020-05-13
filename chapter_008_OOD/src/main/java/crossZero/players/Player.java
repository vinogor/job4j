package crossZero.players;

public interface Player {

    String getName();

    String getSign();

    int getCoordinate(String s);

    int getCurrentVictories();

    void incCurrentVictories();
}
