package game.engine;

import game.dice.Dice;

public class GameBoard {
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private Dice[] dice;

    public GameBoard(Player player1, Player player2,) {
        this.player1 = player1;
        this.player2 = player2;
        dice = new Dice[6];
    }

    public Dice[] getDice() {
        return dice;
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
}
