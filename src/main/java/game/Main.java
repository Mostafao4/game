package game;


import game.engine.*;
import javafx.scene.Camera;

import static game.engine.CLIGameController.printDice;
public class Main
{
    public static void main(String[] args) throws Exception
    {
        CLIGameController gc = new CLIGameController();
        gc.startGame();

    }
}
