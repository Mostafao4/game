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
    private int ABfirst;
    private int ABlast;
    private TimeWarp[] timeWarp;
    private int TWfirst;
    private int TWlast;
    private ElementalCrest[] elementalCrest;
    private int elementalCrestCounter;


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
        arcaneBoost[ABlast++] = new ArcaneBoost();
    }
    public boolean deleteArcaneBoost() {
        if(ABfirst<=ABlast){
            ABfirst++; return true;}
        else return false;
    }
    public TimeWarp[] getTimeWarp() {
        return timeWarp;
    }
    public void addTimeWarp() {
        timeWarp[TWlast++] =  new TimeWarp();
    }
    public boolean deleteTimeWarp() {
        if(TWfirst<=TWlast){
            TWfirst++; return true;}
        else return false;
    }
    public ElementalCrest[] getElementalCrest() {
        return elementalCrest;
    }
    public void addElementalCrest(ElementalCrest ec){
        elementalCrest[elementalCrestCounter++] = ec;
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
}
