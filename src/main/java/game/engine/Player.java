package game.engine;
import game.collectibles.*;
import game.dice.Dice;
public class Player {
    private String playerName;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private Dice selectedDice;
    private int timeWarpCount;
    private int arcaneBoostCount;
    private boolean testFlag;
    public Player(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
        gameScore = new GameScore();
        testFlag = false;
    }
    public Player(String playerName, PlayerStatus playerStatus) {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
        gameScore = new GameScore();
        testFlag = false;
    }


    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }
    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public boolean isTestFlag() {
        return testFlag;
    }

    public void setTestFlag(boolean testFlag) {
        this.testFlag = testFlag;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayer_name(String playerName) {
        this.playerName = playerName;
    }

    public int getTimeWarpCount() {
        return timeWarpCount;
    }
    public void addTimeWarpCount() {
        timeWarpCount++;
    }
    public void subtractTimeWarpCount() {
        timeWarpCount--;
    }

    public int getArcaneBoostCount() {
        return arcaneBoostCount;
    }
    public void addArcaneBoostCount() {
        arcaneBoostCount++;
    }
    public void subtractArcaneBoostCount(){
        arcaneBoostCount--;
    }

    public ElementalCrest[] getElementalCrest() {
        return gameScore.getElementalCrest();
    }

    public Dice getSelectedDice() {
        return selectedDice;
    }
    public void setSelectedDice(Dice selectedDice) {
        this.selectedDice = selectedDice;
    }


    public ScoreSheet getScoreSheet() {
        return gameScore.getScoreSheet();
    }



}
