package game;

import game.engine.*;
import game.dice.Dice;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dice Realms: Quest for the Elemental Crests!");
        CLIGameController gc = new CLIGameController();
        gc.startGame();
        while (gc.getGameBoard().getGameStatus().getRound() <= 6) {
            switch (gc.getGameBoard().getGameStatus().getRound()) {
                case 1:
                    gc.getActivePlayer().addTimeWarp();
                    System.out.println("You received a Time Warp!");
                case 2:
                    gc.getActivePlayer().addArcaneBoost();
                    System.out.println("You received a Time Warp!");
                case 3:
                    gc.getActivePlayer().addTimeWarp();
                    System.out.println("You received a Time Warp!");
                case 4:
                    gc.getActivePlayer().addEssenceBonus();
                    System.out.println("You received a Time Warp!");
                default:
            }
            while (gc.getGameBoard().getGameStatus().getTurn() <= 3 && gc.areThereAvailableDice()) {
                gc.getGameBoard().setDice(gc.rollDice());
                System.out.println("Do you want to use Time Warp?");
                String input = sc.nextLine();
                if (input.equals("yes"))
                    gc.getGameBoard().setDice(gc.rollDice());
                System.out.println(gc.getActivePlayer().getScoreSheet());
                System.out.println("Select a die");
                int intput = sc.nextInt();
                switch (intput) {
                    case 1:
                        gc.selectDice(gc.getGameBoard().getDice()[1], gc.getActivePlayer());
                        break;
                    case 2:
                        gc.selectDice(gc.getGameBoard().getDice()[2], gc.getActivePlayer());
                        break;
                    case 3:
                        gc.selectDice(gc.getGameBoard().getDice()[3], gc.getActivePlayer());
                        break;
                    case 4:
                        gc.selectDice(gc.getGameBoard().getDice()[4], gc.getActivePlayer());
                        break;
                    case 5:
                        gc.selectDice(gc.getGameBoard().getDice()[5], gc.getActivePlayer());
                        break;
                    case 6:
                        gc.selectDice(gc.getGameBoard().getDice()[6], gc.getActivePlayer());
                        break;
                    default:
                }
                gc.makeMove(gc.getActivePlayer(), new Move(gc.getGameBoard().getSelectedDice(), gc.getGameBoard().getSelectedDice().getRealm()));


                System.out.println(gc.getPassivePlayer().getScoreSheet());
                System.out.println("Select a die from the forgotten realm");
                intput = sc.nextInt();
                gc.makeMove(gc.getPassivePlayer(),new Move(gc.getForgottenRealmDice()[intput],gc.getForgottenRealmDice()[intput].getRealm()));
                System.out.println("Does "+gc.getActivePlayer().getPlayerName()+" want to use Arcane Boost?");
                input = sc.nextLine();
                if(input.equals("yes")) {
                    //use arcane boost
                }

                System.out.println("Does "+gc.getPassivePlayer().getPlayerName()+" want to use Arcane Boost?");
                input = sc.nextLine();
                if(input.equals("yes")) {
                    //use arcane boost
                }
                gc.switchPlayer();
                gc.getGameStatus().resetTurn();
                if(gc.getGameStatus().getPartOfRound() == 0)
                    gc.getGameStatus().incrementPartOfRound();

                else{
                    gc.getGameStatus().incrementRound();
                    gc.getGameStatus().resetPartofRound();
                    break;
                    }
                }
            }
        int s1 = gc.getGameBoard().getPlayer1().getGameScore().getScore();
        int s2 = gc.getGameBoard().getPlayer2().getGameScore().getScore();
        if(s1>s2)
            System.out.println(gc.getGameBoard().getPlayer1()+" has won the game!");
        else
            System.out.println(gc.getGameBoard().getPlayer2()+" has won the game!");
        }

    }

