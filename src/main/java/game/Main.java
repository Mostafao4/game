package game;

import game.collectibles.Bonus;
import game.creatures.Realm;
import game.engine.*;
import game.dice.Dice;
import game.exceptions.PlayerActionException;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws PlayerActionException {
        Scanner sc = new Scanner(System.in);
        String input = "";
        int intput = 0;
        System.out.println("Dice Realms: Quest for the Elemental Crests!");
        CLIGameController gc = new CLIGameController();
        gc.startGame();
        while (gc.getGameBoard().getGameStatus().getRound() <= 6) {
            switch (gc.getGameBoard().getGameStatus().getRound()) {
                case 1:
                case 3:
                    gc.getActivePlayer().addTimeWarpCount();
                    System.out.println("You received a Time Warp!"); break;
                case 2:
                    gc.getActivePlayer().addArcaneBoostCount();
                    System.out.println("You received an Arcane Boost!"); break;
                case 4:
                    System.out.println("You received an Essence Bonus!");
                    System.out.println("Choose a realm to attack");
                    String s = sc.nextLine();
                    Realm r = getRealmFromString(s);
                    int h = sc.nextInt();
                    gc.useBonus(new Bonus(r), h); break;
                default: System.out.println("You did not receive any reward");
            }
            while (gc.getGameBoard().getGameStatus().getTurn() <= 3) {
                gc.getGameBoard().setDice(gc.rollDice());
                System.out.println("Do you want to use Time Warp?");
                if(sc.hasNextLine())
                    input = sc.nextLine();
                if (input.equals("yes"))
                    gc.getGameBoard().setDice(gc.rollDice());
                gc.getActivePlayer().getScoreSheet().print();
                System.out.println("Select a die");
                if(sc.hasNextInt())
                    intput = sc.nextInt();
                gc.selectDice(gc.getGameBoard().getDice()[intput], gc.getActivePlayer());
                gc.makeMove(gc.getActivePlayer(), new Move(gc.getActivePlayer().getSelectedDice(), gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getActivePlayer().getSelectedDice())));
                gc.getPassivePlayer().getScoreSheet().print();
                System.out.println("Select a die from the forgotten realm");
                if(sc.hasNextInt())
                    intput = sc.nextInt();
                gc.makeMove(gc.getPassivePlayer(), new Move(gc.getForgottenRealmDice()[intput], gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getForgottenRealmDice()[intput])));
                System.out.println("Does " + gc.getActivePlayer().getPlayerName() + " want to use Arcane Boost?");
                if(sc.hasNextLine())
                    input = sc.nextLine();
                if (input.equals("yes")) {
                    //use arcane boost
                }

                System.out.println("Does " + gc.getPassivePlayer().getPlayerName() + " want to use Arcane Boost?");
                if(sc.hasNextLine())
                    input = sc.nextLine();

                if (input.equals("yes")) {
                    //use arcane boost
                }
                gc.getGameBoard().getGameStatus().incrementTurn();
            }
            gc.switchPlayer();
            gc.getGameBoard().getGameStatus().resetTurn();
                if(gc.getGameStatus().getPartOfRound() == 0)
                    gc.getGameStatus().incrementPartOfRound();
                else{
                    gc.getGameStatus().incrementRound();
                    gc.getGameStatus().resetPartofRound();
                    break;
                    }

            }
        sc.close();
        gc.getGameBoard().getPlayer1().getGameScore().setScore();
        gc.getGameBoard().getPlayer2().getGameScore().setScore();
        int s1 = gc.getGameBoard().getPlayer1().getGameScore().getScore();
        int s2 = gc.getGameBoard().getPlayer2().getGameScore().getScore();
        if(s1>s2)
            System.out.println(gc.getGameBoard().getPlayer1()+" has won the game!");
        else
            System.out.println(gc.getGameBoard().getPlayer2()+" has won the game!");



        }
        public static Realm getRealmFromString(String s){
            switch(s){
                case "red": return Realm.RED;
                case "green": return Realm.GREEN;
                case "blue": return Realm.BLUE;
                case "magenta": return Realm.MAGENTA;
                default: return Realm.YELLOW;
            }
        }

    }




