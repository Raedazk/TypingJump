package typingjump;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah Jabr
public class RegisterationController implements Initializable {

    @FXML
    private JFXTextField playerName;
    @FXML
    private JFXRadioButton rdFemale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXRadioButton rdMale;
    @FXML
    private JFXComboBox comboxType;
    @FXML
    private ImageView checkFemale;
    @FXML
    private ImageView checkMale;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //________________Filling ComboBox___________________________ 
        ObservableList t = FXCollections.observableArrayList("letter", "word", "sentence");
        comboxType.getItems().addAll(t);
        comboxType.getSelectionModel().select(0);//default item letter
        //_______________________________________________________
        //Check the female to put the mark
        rdFemale.setOnAction(e -> {

            checkFemale.setVisible(true);
            checkMale.setVisible(false);
        });
        //Check the male to put the mark
        rdMale.setOnAction(e -> {
            checkFemale.setVisible(false);
            checkMale.setVisible(true);
        });
    }

    @FXML
    private void AddTextBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addPlayText.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void HowToPlayBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        if (validateName()) {

            //________________create New Player Object___________________________
            player p = new player();

            //________________Insert Name___________________________
            p.setUser_name(playerName.getText());

            //ayoutX="523.0" layoutY="188.0"
            //________________Insert Gender___________________________
            if (gender.getSelectedToggle().equals(rdMale)) {
                p.setGender("male");

            } else if (gender.getSelectedToggle().equals(rdFemale)) {
                p.setGender("female");
            }

            //________________Insert Type___________________________
            if (comboxType.getSelectionModel().isSelected(0)) {
                p.setType("letter");
            } else if (comboxType.getSelectionModel().isSelected(1)) {
                p.setType("word");
            } else if (comboxType.getSelectionModel().isSelected(2)) {
                p.setType("sentence");
            }

            GameBoardController.setMovePlayerData(p);
            //start game
            FXMLLoader load = new FXMLLoader();

            load.setLocation(getClass().getResource("GameBoard.fxml"));
            Parent resultparent = load.load();
            Object controller = load.getController();
            Scene scene = new Scene(resultparent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void WatchVideoBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TipsVideo.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void TopThreeBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("highScores.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //________________________________
    //validation name
    private boolean validateName() {
        Pattern pat = Pattern.compile("[a-z A-Z]+");
        Matcher m = pat.matcher(playerName.getText());
        if (m.find() && m.group().equals(playerName.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your name");
            alert.showAndWait();
            return false;
        }
    }

    //_______________________________________
    // Exit button
    @FXML
    private void ExitBtn(ActionEvent event) throws IOException {

        HibernateUtilPlayText.getSessionFactory().close();
        HibernateUtilPlayer.getSessionFactory().close();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
