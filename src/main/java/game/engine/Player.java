package game.engine;
import game.collectibles.*;
import game.creatures.Realm;
import game.dice.Dice;
import java.util.Scanner;
public class Player {
    private String playerName;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private Dice selectedDice;
    private int timeWarpCount;
    private int arcaneBoostCount;
    private ElementalCrest[] elementalCrest;
    private int c;
    public Player(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
        elementalCrest = new ElementalCrest[5];
        gameScore = new GameScore();

    }
    public Player(String playerName, PlayerStatus playerStatus) {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
        elementalCrest = new ElementalCrest[5];
        gameScore = new GameScore();
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
        return elementalCrest;
    }
    public void addElementalCrest(Realm r){
        elementalCrest[c++] = new ElementalCrest(r);
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
