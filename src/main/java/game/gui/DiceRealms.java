package game.gui;

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
        FXMLLoader loader2= new FXMLLoader(getClass().getResource("/modescene.fxml"));
        FXMLLoader loader3= new FXMLLoader(getClass().getResource("/SceneBlue.fxml"));
        FXMLLoader loader4= new FXMLLoader(getClass().getResource("/SceneGreen.fxml"));
        FXMLLoader loader5= new FXMLLoader(getClass().getResource("/SceneMagenta.fxml"));
        FXMLLoader loader6= new FXMLLoader(getClass().getResource("/SceneRed.fxml"));
        FXMLLoader loader7= new FXMLLoader(getClass().getResource("/SceneYellow.fxml"));
        // loader.setController(new contoller());
        Scene scene1 = new Scene(loader.load());
        Scene scene2 = new Scene(loader1.load());
        Scene scene3 = new Scene(loader2.load());
        Scene scene4 = new Scene(loader3.load());
        Scene scene5 = new Scene(loader4.load());
        Scene scene6 = new Scene(loader5.load());
        Scene scene7 = new Scene(loader6.load());
        Scene scene8 = new Scene(loader7.load());

        Image icon = new Image("/Dice realms.jpeg");
        // Image background = new Image("/Dice realms.jpeg");
        // ImageView imageView = new ImageView(background);

            
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Dice Realms: Quest for the Elemental Crests!");
        primaryStage.getIcons().add(icon);
        
        primaryStage.show();
    }

   public static void main(String[] args) {
       launch(args);
   }
}
