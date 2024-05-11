package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.dice.DiceStatus;

import java.util.Scanner;

public class CLIGameController extends GameController {

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
        GameBoard gameBoard = new GameBoard(p1,p2);
    }

    @Override
    public boolean switchPlayer() {

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
        for(int i = 0; i < dice.length; i++){
            if(dice[])
        }
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
        return null;
    }

    @Override
    public Player getActivePlayer() {
        return null;
    }

    @Override
    public Player getPassivePlayer() {
        return null;
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        return null;
    }

    @Override
    public GameScore getGameScore(Player player) {
        return null;
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        return new TimeWarp[0];
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        return new ArcaneBoost[0];
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        return false;
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        return false;
    }
}
