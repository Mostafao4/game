package game.engine;

public class GameStatus {
    private int round;
    private int turn;
    private boolean status;

    public GameStatus(int round, int turn, boolean status) {}

    @Override
    public String toString() {
        String s = status?"Game is ongoing.":"Game has ended.";
        return "Round number: " + round + "/nTurn: " + turn + "/nStatus: " + s;
    }
}
