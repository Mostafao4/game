package game.gui;


import java.util.Random;

import game.engine.CLIGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DiceRealms extends Application {
    public static Stage stage;
    public controller c;
    private int turnCounter = 1;      
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        c = new controller();
        // c.start();  
       try{
                Parent root = FXMLLoader.load(getClass().getResource("/Mainmenu.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setTitle("Dice Realms: Quest for the Elemental Crests!");
                Image icon = new Image("/Dice realms.jpeg");

                SoundManager.playBackgroundMusic();

                primaryStage.getIcons().add(icon);
                primaryStage.setScene(scene);
                primaryStage.show();   
            }
        
            catch(Exception e){
                e.printStackTrace();
            }   
    }

    public void stop() throws Exception {
        // Stop the music when the application is closed
        SoundManager.stopBackgroundMusic();
        super.stop();
    }
    
   public static void main(String[] args) {
       launch(args);
   }
}
