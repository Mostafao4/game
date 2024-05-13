package game.engine;

public class GameStatus {
    private int round;
    private int turn;
    private boolean status;

    public GameStatus() {
        round = 1;
        turn = 1;
    }

    @Override
    public String toString() {
        String s = status?"Game is ongoing.":"Game has ended.";
        return "Round number: " + round + "/nTurn: " + turn + "/nStatus: " + s;
    }


    public int getRound() {
        return round;
    }

    public void incrementRound(int round) {
        round++;
        turn = 1;
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        turn++;
    }

    public void resetTurn(int turn) {
        turn = 1;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
