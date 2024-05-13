package game.engine;

import game.dice.Dice;

public class GameBoard {
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private Dice[] dice;
    private Dice selectedDice;

    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        dice = new Dice[6];
    }

    public Dice[] getDice() {
        return dice;
    }
    public void setDice(Dice[] dice) {
        this.dice = dice;
    }

    public Dice getSelectedDice() {
        return selectedDice;
    }
    public void setSelectedDice(Dice selectedDice) {
        this.selectedDice = selectedDice;
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
