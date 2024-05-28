package game.gui;

import java.lang.ModuleLayer.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DiceRealms extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Mainmenu.fxml"));
        
        FXMLLoader loader1= new FXMLLoader(getClass().getResource("/mainscene.fxml"));
        Scene scene2 = new Scene(loader1.load());
        

     
        // loader.setController(new controller());
        Scene scene1 = new Scene(loader.load());
        
        

        Image icon = new Image("/Dice realms.jpeg");
        // Image background = new Image("/Dice realms.jpeg");
        // ImageView imageView = new ImageView(background);

        // primaryStage.setScene(scene1);
        primaryStage.setScene(scene2);    
        // primaryStage.setScene(scene3);
        primaryStage.setTitle("Dice Realms: Quest for the Elemental Crests!");
        primaryStage.getIcons().add(icon);
        
        primaryStage.show();
    }

   public static void main(String[] args) {
       launch(args);
   }
}
