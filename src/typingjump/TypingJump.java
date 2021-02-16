package typingjump;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah
 * Jabr
 */
public class TypingJump extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("registeration.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Required Files");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure that:\nyou change the connection configration accourding to your database\nadd the external libraries (jfeonix 8 + MySQL connecter 5)\n then Try Again\n Thank you");
            alert.showAndWait();
        }
    }

}
