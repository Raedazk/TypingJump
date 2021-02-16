package typingjump;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
// @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah Jabr

public class AddPlayTextController implements Initializable {

    @FXML
    private JFXTextField fdNewText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    //_____Add To DataBase__________

    @FXML
    private void oK(ActionEvent event) throws IOException {
        if (validateText()) {
            playtext p = new playtext();

//________________Insert Type___________________________
            if (fdNewText.getText().length() == 1) {
                p.setType("letter");
            } else if (fdNewText.getText().length() > 0 && !(fdNewText.getText().contains(" "))) {
                p.setType("word");
            } else if (fdNewText.getText().length() > 0 && fdNewText.getText().contains(" ")) {
                p.setType("sentence");
            }
            p.setText(fdNewText.getText());
//________________Create Session___________________________
            final Session s1 = HibernateUtilPlayText.getSessionFactory().openSession();
            final Transaction t1 = s1.beginTransaction();
            s1.save(p);
            t1.commit();
            s1.close();
            //_____Next Scene__________
            Parent root = FXMLLoader.load(getClass().getResource("Registeration.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    //________________________________________

    @FXML  //Button cancel
    private void cancel(ActionEvent event) throws IOException {
        //_____Next Scene__________
        Parent root = FXMLLoader.load(getClass().getResource("Registeration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //______________________________________
    //validate Text Field
    private boolean validateText() {
        Pattern pat = Pattern.compile("[a-z A-Z]+");
        Matcher m = pat.matcher(fdNewText.getText());
        if (m.find() && m.group().equals(fdNewText.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate TetxField");
            alert.setHeaderText(null);
            alert.setContentText("No Text Exist ");
            alert.showAndWait();
            return false;
        }
    }

}
