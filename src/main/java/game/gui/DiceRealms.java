package game.gui;


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
    public static CLIGameController cliGameController;
    @Override
    public void start(Stage primaryStage) throws Exception {
       stage = primaryStage;
       try{
                Parent root = FXMLLoader.load(getClass().getResource("/Mainmenu.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setTitle("Dice Realms: Quest for the Elemental Crests!");
                Image icon = new Image("/Dice realms.jpeg");
                primaryStage.getIcons().add(icon);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }

   public static void main(String[] args) {
       launch(args);
   }
}
