//Assign3Draw

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.collections.*;

public class Assign3Draw extends Application {
   @Override
   public void start(Stage stage) throws Exception {
	  
	  Parent root = 
         FXMLLoader.load(getClass().getResource("Assign3Draw.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Assign3Draw"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      // create a TipCalculator object and call its start method
	  
      launch(args); 
   }
}
