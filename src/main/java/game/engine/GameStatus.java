package game.engine;

public class GameStatus {
    private int round;
    private int turn;
    private int partOfRound;
    private boolean status;
    private boolean av;

    public GameStatus() {
        round = 1;
        turn = 1;
        status = true;
        av = true;
    }

    @Override
    public String toString() {
        String s = status?"Game is ongoing.":"Game has ended.";
        return "Round: " + round + "/nTurn: " + turn + "/nStatus: " + s;
    }

    public boolean isAv() {
        return av;
    }

    public void setAv(boolean av) {
        this.av = av;
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
