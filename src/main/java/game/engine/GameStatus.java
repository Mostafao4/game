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


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
