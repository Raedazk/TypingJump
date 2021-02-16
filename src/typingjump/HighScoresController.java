package typingjump;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @authors Raeda azkoul, Noor Al-Hafez, Fatema Bayat, Aisha Farouque, Fatimah
 * Jabr
 */
public class HighScoresController implements Initializable {

    @FXML
    private TableView<player> highScoreTable;
    @FXML
    private TableColumn<player, String> colName;
    @FXML
    private TableColumn<player, Double> colScore;
    @FXML
    private TableColumn<player, String> colType;
    @FXML
    private TableColumn<player, Integer> colTime;
    @FXML
    private TableColumn<player, Double> colSpeed;
    @FXML
    private TableColumn<player, Double> colAccuracy;
    @FXML
    private TableColumn<player, Integer> colNoChars;
    @FXML
    private Button highScoreExit;
    @FXML
    private Button Reset;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        queryResult();
        highScoreExit.setOnAction(e -> {
            back();
        });
        Reset.setOnAction(e -> {
            deleteResult();
            back();
        });
    }

    private void back() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registeration.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HighScoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteResult() {
        final Session session = HibernateUtilPlayer.getSessionFactory().openSession();
        String hqlDel = "delete from player;";
        Query queryDel = session.createSQLQuery(hqlDel);
        int resultD = queryDel.executeUpdate();
        System.out.println("players recoeds deleted: " + resultD);
        session.close();
    }

    private void queryResult() {
        final Session session = HibernateUtilPlayer.getSessionFactory().openSession();
        List<player> pList = null;
        String queryStr = "from player as p order by p.score DESC";
        Query query = session.createQuery(queryStr);
        pList = query.list();
        ObservableList<player> list = FXCollections.observableArrayList(pList);
        session.close();
        highScoreTable.setItems(list);
    }

    private void initCol() {
        colName.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        colAccuracy.setCellValueFactory(new PropertyValueFactory<>("accuracy"));
        colNoChars.setCellValueFactory(new PropertyValueFactory<>("no_chars"));
    }
}
