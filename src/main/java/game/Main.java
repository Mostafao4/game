package game;

import game.collectibles.Bonus;
import game.creatures.Realm;
import game.dice.GreenDice;
import game.engine.*;

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

            while (gc.getGameBoard().getGameStatus().getTurn() <= 3) {
                gc.getGameBoard().setDice(gc.rollDice());

                System.out.println("Do you want to use Time Warp?");
                if (gc.getGameBoard().getScan().string().equals("yes"))
                    gc.getGameBoard().setDice(gc.rollDice());

                System.out.println(gc.getActivePlayer().getScoreSheet());
                System.out.println("Select a die");
                i = gc.getGameBoard().getScan().num();
                gc.selectDice(gc.getGameBoard().getDice()[i], gc.getActivePlayer());
                if (i == 0) {
                    System.out.println("Select a dragon to attack");
                    i = gc.getGameBoard().getScan().num();
                    gc.makeMove(gc.getActivePlayer(), new Move(gc.getActivePlayer().getSelectedDice(), gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getActivePlayer().getSelectedDice()), i));
                } else if (i == 1) {
                    int q = gc.getAllDice()[1].getValue();
                    int w = gc.getAllDice()[5].getValue();
                    System.out.println(q+w);
                    gc.makeMove(gc.getActivePlayer(), new Move(new GreenDice(q + w), gc.getActivePlayer().getScoreSheet().getGaia()));
                } else
                    gc.makeMove(gc.getActivePlayer(), new Move(gc.getActivePlayer().getSelectedDice(), gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getActivePlayer().getSelectedDice())));


                if (gc.getGameBoard().getGameStatus().getRound() != 1 && gc.getGameBoard().getGameStatus().getPartOfRound() != 0) {
                    System.out.println("Select a die from the forgotten realm");
                    i = gc.getGameBoard().getScan().num();
                    gc.makeMove(gc.getPassivePlayer(), new Move(gc.getForgottenRealmDice()[i], gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getForgottenRealmDice()[i])));
                }

                System.out.println("Does " + gc.getActivePlayer().getPlayerName() + " want to use Arcane Boost?");
                s = gc.getGameBoard().getScan().string();
                if (s.equals("yes")) {
                    //use arcane boost
                }

                System.out.println("Does " + gc.getPassivePlayer().getPlayerName() + " want to use Arcane Boost?");
                s = gc.getGameBoard().getScan().string();

                if (s.equals("yes")) {
                    //use arcane boost
                }
                gc.getGameBoard().getGameStatus().incrementTurn();
            }
            gc.switchPlayer();
            gc.getGameBoard().getGameStatus().resetTurn();
            if (gc.getGameStatus().getPartOfRound() == 0)
                gc.getGameStatus().incrementPartOfRound();
            else {
                gc.getGameStatus().incrementRound();
                gc.getGameStatus().resetPartofRound();
                break;
            }


            gc.getGameBoard().getPlayer1().getGameScore().setScore();
            gc.getGameBoard().getPlayer2().getGameScore().setScore();
            int sc1 = gc.getGameBoard().getPlayer1().getGameScore().getScore();
            int sc2 = gc.getGameBoard().getPlayer2().getGameScore().getScore();
            if (sc1 > sc2)
                System.out.println(gc.getGameBoard().getPlayer1() + " has won the game!");
            else
                System.out.println(gc.getGameBoard().getPlayer2() + " has won the game!");


        }
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




