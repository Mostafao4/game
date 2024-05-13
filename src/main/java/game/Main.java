package game;

import game.engine.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dice Realms: Quest for the Elemental Crests!");     
        CLIGameController x = new CLIGameController();
        x.startGame();
        while(x.getGameBoard().getGameStatus().getRound()<=6){

        }
    }
}
