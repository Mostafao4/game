package game.engine;
import game.collectibles.ArcaneBoost;
import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.Dice;

import java.util.Scanner;
public class Player {
    private String playerName;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private Dice selectedDice;
    private ArcaneBoost[] arcaneBoost;
    private TimeWarp[] timeWarp;
    private ElementalCrest[] elementalCrest;


    public Player(String playerName, PlayerStatus playerStatus) {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
        arcaneBoost = new ArcaneBoost[10];
        timeWarp = new TimeWarp[10];
        elementalCrest = new ElementalCrest[5];
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
    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public String getPlayer_name() {
        return playerName;
    }
    public void setPlayer_name(String playerName) {
        this.playerName = playerName;
    }

    public Dice getSelectedDice() {
        return selectedDice;
    }
    public void setSelectedDice(Dice selectedDice) {
        this.selectedDice = selectedDice;
    }

    public ArcaneBoost[] getArcaneBoost() {
        return arcaneBoost;
    }
    public void setArcaneBoost(ArcaneBoost[] arcaneBoost) {
        this.arcaneBoost = arcaneBoost;
    }
    public TimeWarp[] getTimeWarp() {
        return timeWarp;
    }
    public void setTimeWarp(TimeWarp[] timeWarp) {
        this.timeWarp = timeWarp;
    }
    public ElementalCrest[] getElementalCrest() {
        return elementalCrest;
    }
    public void setElementalCrest(ElementalCrest[] elementalCrest) {
        this.elementalCrest = elementalCrest;
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
}
