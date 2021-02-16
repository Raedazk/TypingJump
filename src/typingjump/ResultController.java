package typingjump;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
// * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah Jabr

public class ResultController implements Initializable {

    @FXML
    private Label user_name;
    @FXML
    private Label score;
    @FXML
    private Label time;
    @FXML
    private Label speed;
    @FXML
    private Label accuracy;
    @FXML
    private Label no_chars;
    @FXML
    private Label type;

    private static player movePlayerData = new player();

    public static void setMovePlayerData(player movePlayerData) {
        ResultController.movePlayerData = movePlayerData;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user_name.setText(movePlayerData.getUser_name());
        score.setText(Double.toString(movePlayerData.getScore()));
        time.setText(new DecimalFormat("##.##").format(movePlayerData.getTime()));
        speed.setText(new DecimalFormat("##.##").format(movePlayerData.getSpeed()));
        accuracy.setText(new DecimalFormat("##.##").format(movePlayerData.getAccuracy()));
        no_chars.setText(Integer.toString(movePlayerData.getNo_chars()));
        type.setText(movePlayerData.getType());

    }

    @FXML
    private void okbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registeration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
