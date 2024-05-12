package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.dice.DiceStatus;

import java.util.Scanner;

public class CLIGameController extends GameController {
    private GameBoard gameBoard;

    @Override
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dice Realms: Quest for Elemental Crests!!!");
        System.out.println("Enter Player 1 name");
        String s1 = scanner.nextLine();
        System.out.println("Enter Player 2 name");
        String s2 = scanner.nextLine();
        scanner.close();
        Player p1 = new Player(s1,PlayerStatus.ACTIVE);
        Player p2 = new Player(s2,PlayerStatus.PASSIVE);
        gameBoard = new GameBoard(p1,p2);
    }

    @Override
    public boolean switchPlayer() {
        this.getActivePlayer().setPlayerStatus(PlayerStatus.PASSIVE);
        this.getPassivePlayer().setPlayerStatus(PlayerStatus.ACTIVE);
        return true;
    }

    @Override
    public Dice[] rollDice() {
        Dice[] dice = this.getAvailableDice();
        for (Dice die : dice)
            if (die != null)
                die.roll();
        return dice;
    }

    @Override
    public Dice[] getAvailableDice() {
        Dice[] array = getGameBoard().getDice();
        for(int i = 0; i < array.length; i++)

            if(array[i].getDiceStatus() != DiceStatus.AVAILABLE)
                array[i]=null;
        return array;
    }

    @Override
    public Dice[] getAllDice() {
        return getGameBoard().getDice();
    }

    @Override
    public Dice[] getForgottenRealmDice() {
        Dice[] dice = this.getAvailableDice();
        for(int i = 0; i < dice.length; i++)
            if(dice[i].getDiceStatus()!=DiceStatus.FORGOTTEN_REALM)
                dice[i]=null;
        return dice;
    }

    @Override
    public Move[] getAllPossibleMoves(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        return new Move[0];
    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public Player getActivePlayer() {
        if(gameBoard.getPlayer1().getPlayerStatus()==PlayerStatus.ACTIVE)
            return gameBoard.getPlayer1();
        else
            return gameBoard.getPlayer2();
    }

    @Override
    public Player getPassivePlayer() {
        if(gameBoard.getPlayer1().getPlayerStatus()==PlayerStatus.PASSIVE)
            return gameBoard.getPlayer1();
        else
            return gameBoard.getPlayer2();
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return player.getScoreSheet();
    }

    @Override
    public GameStatus getGameStatus() {
        return gameBoard.getGameStatus();
    }

    @Override
    public GameScore getGameScore(Player player) {
        return player.getGameScore();
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        return player.getTimeWarp();
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        return player.getArcaneBoost();
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        Dice[] diceArray = this.getAvailableDice();
        player.setSelectedDice(dice);
        int x = dice.getValue();
        for (Dice value : diceArray) {
            int y = value.getValue();
            if (y < x)
                value.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
        }
        return true;
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        return false;
    }
}
