package game.engine;

public class GameStatus {
    private int round;
    private int turn;
    private int partOfRound;
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
    public void incrementRound() {
        round++;
        turn = 1;
    }


    public int getPartOfRound() {
        return partOfRound;
    }
    public void incrementPartOfRound() {
        partOfRound++;
    }
    public void resetPartofRound(){
        partOfRound = 0;
    }

    public int getTurn() {
        return turn;
    }
    public void incrementTurn() {
        turn++;
    }
    public void resetTurn() {
        turn = 1;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
