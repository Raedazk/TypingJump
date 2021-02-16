package typingjump;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah
 * Jabr
 */
public class AboutController implements Initializable {

    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private Label lblAbout;

    String[] infoAbout;
    private int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        infoAbout = new String[]{"Welcome to\n the Typing jump game.",
            "Our game is about\n practice writing \n(letters, words and\n scentences) \nto increase your \nwriting speed.",
            "It will display various\n letters, words or texts \non the rocks.\n depending on your \nchoice of game type.",
            "You must enter letters\n that match the letters \n  on the rocks.",
            "If they are completely\n identical, you will move \nto the next rock\n and get points and\n more time to play.",
            "And if it doesn't match, \nyou will lose one \nof the hearts\n you have available.",
            "If you lose all hearts, \nthe game will end.\nand your name, score,\n speed and accuracy \nwill be displayed\n at the end of the game. ",
            "When you want \nto end the game\n while playing,\n press the exit button.",
            "To play,\n you should register \nyour data, \nchoose the type of\nthe game, and\n press the start button.",
            "Typing jump Authors\n Raeda Azkoul\n Noor Al - Hafez\nFatimah Jabr\nFatema Bayat\n Aisha Farouqui"};

        lblAbout.setText(infoAbout[0]);

        //______________________________________________________________________
        btnPrevious.setOnAction(e -> {//previous

            if (count == 0) {
                lblAbout.setText((infoAbout[0]));
            } else {
                count--;
                lblAbout.setText((infoAbout[count]));
            }
        });
        //______________________________________________________________________
        btnNext.setOnAction(e -> {//next

            if (count == infoAbout.length - 1) {
                lblAbout.setText((infoAbout[infoAbout.length - 1]));
            } else {
                count++;
                lblAbout.setText((infoAbout[count]));
            }
        });

    }

    @FXML
    private void btnExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registeration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
