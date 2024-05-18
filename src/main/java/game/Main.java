package game;


import game.engine.*;
import static game.engine.CLIGameController.printDice;
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
//inner loop
            while (gc.getGameBoard().getGameStatus().getTurn() <= 3 && gc.thereAreAvailableDice()) {
               // START TURN AND ROLL DICE
                gc.startTurn();
                gc.rollDice();
                printDice(gc.getAvailableDice());
                // TIME WARP
                if(gc.getActivePlayer().getTimeWarpCount()>0) {
                    System.out.println("Do you want to use Time Warp?");
                    s = gc.getGameBoard().getScan().string();
                    if (s.equals("yes")) {
                        gc.getActivePlayer().subtractTimeWarpCount();
                        gc.rollDice();
                        printDice(gc.getAvailableDice());
                    }
                }
                // NORMAL CHOOSE
                System.out.println("Select a die: ");
                i = gc.getGameBoard().getScan().num();
                gc.selectDice(gc.getAvailableDice()[i], gc.getActivePlayer());
                gc.makeMove(gc.getActivePlayer(), new Move(gc.getActivePlayer().getSelectedDice(), gc.getActivePlayer().getScoreSheet().getCreatureByRealm(gc.getActivePlayer().getSelectedDice())));
                gc.getGameBoard().getGameStatus().incrementTurn();
            }//end of inner loop


            //FORGOTTEN REALM
            System.out.println(gc.getPassivePlayer().getPlayerName()+ ", select a die from the forgotten realm: ");
            printDice(gc.getForgottenRealmDice());
            i = gc.getGameBoard().getScan().num();
            gc.makeMove(gc.getPassivePlayer(), new Move(gc.getForgottenRealmDice()[i], gc.getPassivePlayer().getScoreSheet().getCreatureByRealm(gc.getForgottenRealmDice()[i])));



            //ARCANE BOOST
            if(gc.getActivePlayer().getArcaneBoostCount()>0) {
                System.out.println("Does " + gc.getActivePlayer().getPlayerName() + " want to use Arcane Boost?");
                s = gc.getGameBoard().getScan().string();
                if (s.equals("yes")) {
                    gc.getActivePlayer().subtractArcaneBoostCount();
                    printDice(gc.getAllDice());
                    gc.useArcaneBoost(gc.getActivePlayer());
                }
            }
            if(gc.getPassivePlayer().getArcaneBoostCount()>0) {
                System.out.println("Does " + gc.getPassivePlayer().getPlayerName() + " want to use Arcane Boost?");
                s = gc.getGameBoard().getScan().string();
                if (s.equals("yes")) {
                    gc.getPassivePlayer().subtractArcaneBoostCount();
                    printDice(gc.getAllDice());
                    gc.useArcaneBoost(gc.getPassivePlayer());
                }
            }




            gc.resetDice();
            gc.switchPlayer();
            if (gc.getGameStatus().getPartOfRound() == 0)
                gc.getGameStatus().incrementPartOfRound();
            else {
                gc.getGameStatus().incrementRound();
                gc.getGameStatus().resetPartofRound();
            }
        } // end of outer loop
            gc.getGameBoard().getPlayer1().getGameScore().setScore();
            gc.getGameBoard().getPlayer2().getGameScore().setScore();
            int sc1 = gc.getGameBoard().getPlayer1().getGameScore().getScore();
            int sc2 = gc.getGameBoard().getPlayer2().getGameScore().getScore();
            if (sc1 > sc2)
                System.out.println(gc.getGameBoard().getPlayer1().getPlayerName() + " has won the game!");
            else
                System.out.println(gc.getGameBoard().getPlayer2().getPlayerName() + " has won the game!");



    }


    }




