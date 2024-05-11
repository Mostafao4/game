package game.engine;
import game.dice.Dice;

import java.util.Scanner;
public class Player {
    private String playerName;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    private PlayerStatus playerStatus;
    private Dice selectedDice;


    public Player(String playerName, PlayerStatus playerStatus) {
        this.playerName = playerName;
        this.playerStatus = playerStatus;
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

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }
}
