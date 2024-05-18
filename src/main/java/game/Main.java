package game;

import game.collectibles.Bonus;
import game.creatures.Realm;
import game.dice.Dice;
import game.dice.GreenDice;
import game.engine.*;

import static game.engine.CLIGameController.printDice;
import static game.engine.Scan.*;


public class Main {
    public static void main(String[] args) throws Exception {
        CLIGameController gc = new CLIGameController();
        gc.startGame();
        int i = 0;
        String s = "";

        while (gc.getGameBoard().getGameStatus().getRound() <= 6) {
            switch (gc.getGameBoard().getGameStatus().getRound()) {
                case 1:
                case 3:
                    gc.getActivePlayer().addTimeWarpCount();
                    System.out.println("You received a Time Warp!");
                    break;
                case 2:
                    gc.getActivePlayer().addArcaneBoostCount();
                    System.out.println("You received an Arcane Boost!");
                    break;
                case 4:
//                    System.out.println("You received an Essence Bonus!");
//                    System.out.println("Choose a realm to attack");
//                    Realm r = getRealmFromString(s);
//                    gc.useBonus(new Bonus(r), h);
                    break;
                default:
                    System.out.println("You did not receive any reward");
            }

            while (gc.getGameBoard().getGameStatus().getTurn() <= 3 && gc.thereAreAvailableDice()) {
                System.out.println("Round: " + gc.getGameBoard().getGameStatus().getRound());
                System.out.println("Turn: " + gc.getGameBoard().getGameStatus().getTurn());
                System.out.println("Current Player: " + gc.getActivePlayer().getPlayerName());
                System.out.println();

                gc.rollDice();
                printDice(gc.getAvailableDice());

//                System.out.println("Do you want to use Time Warp?");
//                if (gc.getGameBoard().getScan().string().equals("yes"))
//                    gc.getGameBoard().setDice(gc.rollDice());

                System.out.println("Select a die");

                i = gc.getGameBoard().getScan().num();
                gc.selectDice(gc.getGameBoard().getDice()[i], gc.getActivePlayer());
                //System.out.println(gc.getGameBoard().getDice()[0].getValue());
                gc.makeMove(gc.getActivePlayer(), new Move(gc.getActivePlayer().getSelectedDice(), gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getActivePlayer().getSelectedDice())));


                if (gc.getGameBoard().getGameStatus().getRound() != 1 && gc.getGameBoard().getGameStatus().getPartOfRound() != 0) {
                    System.out.println("Select a die from the forgotten realm");
                    gc.getForgottenRealmDice();
                    i = gc.getGameBoard().getScan().num();
                    gc.makeMove(gc.getPassivePlayer(), new Move(gc.getForgottenRealmDice()[i], gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getForgottenRealmDice()[i])));
                }

//                System.out.println("Does " + gc.getActivePlayer().getPlayerName() + " want to use Arcane Boost?");
//                s = gc.getGameBoard().getScan().string();
//                if (s.equals("yes"))
//                    gc.useArcaneBoost(gc.getActivePlayer());
//
//
////                System.out.println("Does " + gc.getPassivePlayer().getPlayerName() + " want to use Arcane Boost?");
////                s = gc.getGameBoard().getScan().string();
//
//                if (s.equals("yes"))
//                    gc.useArcaneBoost(gc.getActivePlayer());

                gc.getGameBoard().getGameStatus().incrementTurn();
            }
            gc.switchPlayer();
            if (gc.getGameStatus().getPartOfRound() == 0)
                gc.getGameStatus().incrementPartOfRound();
            else {
                gc.getGameStatus().incrementRound();
                gc.getGameStatus().resetPartofRound();
            }

        }
            gc.getGameBoard().getPlayer1().getGameScore().setScore();
            gc.getGameBoard().getPlayer2().getGameScore().setScore();
            int sc1 = gc.getGameBoard().getPlayer1().getGameScore().getScore();
            int sc2 = gc.getGameBoard().getPlayer2().getGameScore().getScore();
            if (sc1 > sc2)
                System.out.println(gc.getGameBoard().getPlayer1().getPlayerName() + " has won the game!");
            else
                System.out.println(gc.getGameBoard().getPlayer2().getPlayerName() + " has won the game!");



    }
        public static Realm getRealmFromString(String x){
            switch (x) {
                case "red":
                    return Realm.RED;
                case "green":
                    return Realm.GREEN;
                case "blue":
                    return Realm.BLUE;
                case "magenta":
                    return Realm.MAGENTA;
                default:
                    return Realm.YELLOW;
            }
        }

    }




