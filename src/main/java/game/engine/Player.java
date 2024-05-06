package game.engine;
import java.util.Scanner;
public class Player {
    private String playerName;
    private ScoreSheet scoreSheet;
    private GameScore gameScore;
    public Player(String player_name, ScoreSheet scoreSheet, GameScore gameScore) {
        this.playerName = player_name;
        this.scoreSheet = scoreSheet;
        this.gameScore = gameScore;
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player name: ");
        playerName = scanner.nextLine();
        scanner.close();
    }
}
