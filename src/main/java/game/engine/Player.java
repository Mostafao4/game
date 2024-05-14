package game.engine;
import game.collectibles.ArcaneBoost;
import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.creatures.Realm;
import game.dice.Dice;

import java.util.Scanner;
public class Player {
    private String playerName;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private Dice selectedDice;
    private ArcaneBoost[] arcaneBoost;
    private int ABcounter;
    private TimeWarp[] timeWarp;
    private int TWcounter;
    private ElementalCrest[] elementalCrest;


    public Player(String playerName, PlayerStatus playerStatus) {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
        arcaneBoost = new ArcaneBoost[10];
        timeWarp = new TimeWarp[10];
        elementalCrest = new ElementalCrest[]{
                new ElementalCrest(Realm.RED),
                new ElementalCrest(Realm.GREEN),
                new ElementalCrest(Realm.BLUE),
                new ElementalCrest(Realm.MAGENTA),
                new ElementalCrest(Realm.YELLOW),};
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

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayer_name(String playerName) {
        this.playerName = playerName;
    }


    public ArcaneBoost[] getArcaneBoost() {
        return arcaneBoost;
    }
    public void addArcaneBoost() {
        arcaneBoost[ABcounter++] = new ArcaneBoost();
    }
    public void deleteArcaneBoost() {

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
    public void setElementalCrest(ElementalCrest ec){
        Realm r = ec.getRealm();
        for(ElementalCrest e :elementalCrest)
            if(r.equals(e.getRealm()))
                e.setStatus();
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
}
